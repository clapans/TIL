<template>
<nav class="navbar" style="background-color:#0742F2;">
    <div class="container-fluid">
        <img src="../assets/logo.png" alt="" style="width:150px">
        <!-- 나가기 -->
        <button class="btn" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" data-bs-target="#Consultclose">상담종료</button>
    </div>
</nav>
<div class="container">
    <h1>1대1상담</h1>
    <div style="margin-left : 3px; margin-right : 3px;">
        <div class="row">
            <div class="col-8">
                <div class="row justify-content-center mx-3">
                    <video id="videoOutput" autoplay class="col-8 my-3" style="display : inline"
                    ref="videoOutput"></video>
                    <video id="videoInput" autoplay class="col-8 my-3" style="display : inline" muted="true"
                    ref="videoInput"></video>
                </div>
                <div class="row" ref="script" style="display : none">
                    <p>Who is your favorite actor or actress?
                        Describe a particular story about something this person did which you heard about in the news.
                        Begin with some details about the actor or actress and then describe all the details of what occurred,
                        in particular, tell me about thethings that made this experience so memorable to people who like movies.</p>
                </div>
                <div>
                    <div>
                        <button @click.prevent="scriptControl" ref="scriptButton" style="display:none">Script ON</button>
                    </div>
                    <button @click.prevent="muteControl" ref="sound">음소거</button>
                    <button @click.prevent="screenControl" ref="screen">비디오 중지</button>
                </div>
            </div>

            <div ref="chat-page" class="hidden col-4">
                <div class="chat-container">
                    <div class="connecting">
                    </div>
                    <ul ref="messageArea">
                    </ul>
                    <form @submit.prevent="sendMessage" ref="messageForm" name="messageForm">
                        <div class="form-group">
                            <div class="input-group clearfix">
                                <input type="text" ref="messageInput" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                                <button type="submit" class="primary">보내기</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<ConsultCloseModal></ConsultCloseModal>
</template>
<script>
import ConsultCloseModal from '@/components/Modal/ConsultCloseModal.vue';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    data(){
        return {
            dataChannel : ''    
        };
    },
    mounted() {
        var messageArea = this.$refs.messageArea;
        var stompClient = null;
        var username = Math.random().toString(36);
        var room = 'wow';
        var videoInput = this.$refs.videoInput
        var videoOutput = this.$refs.videoOutput
        var script = this.$refs.script
        let role = this.$store.state.auth.user.role
        let inboundStream = null;
        let scriptButton = this.$refs.scriptButton

        const configuration = {
            iceServers: [
                {
                    url: 'turn:3.34.51.116:3478',
                    username : 'myuser',
                    credential : 'mypassword'
                },
                {
                    url : 'stun:stun.l.google.com:19302'
                }
            ]
        }

        var peerConnection = new RTCPeerConnection(configuration);
        this.dataChannel = peerConnection.createDataChannel("dataChannel", { reliable: true });

        this.dataChannel.onerror = function(error) {
            console.log("Error:", error);
        };
        this.dataChannel.onclose = function() {
            console.log("Data channel is closed");
        };
         
        if (role === 'teacher') {
            scriptButton.removeAttribute("style")
        }
        if(username) {
            this.socket = new SockJS('https://localhost:8443/ws');
            this.stompClient = Stomp.over(this.socket);
            this.stompClient.connect({}, () => {
                this.stompClient.subscribe('/topic/public/' + room, onMessageReceived);
                stompClient = this.stompClient
                stompClient.send("/app/chat.addUser",
                    JSON.stringify({sender: username, type: 'JOIN', content: room},
                    {})
                )
                peerConnection.createOffer((offer) => {
                    peerConnection.setLocalDescription(new RTCSessionDescription({
                                sdp : offer.sdp,
                                type : offer.type
                            }));
                    stompClient.send('/topic/public/' + room,
                    JSON.stringify({
                        type : "OFFER",
                        content : offer,
                        sender : username
                    }),
                    {});

                    peerConnection.setLocalDescription(offer);

                }, function(error) {
                    // Handle error here
                });

                peerConnection.onicecandidate = function(event) {
                    if (event.candidate) {
                        stompClient.send('/topic/public/' + room,
                        JSON.stringify({
                            type : "CANDIDATE",
                            content : event.candidate,
                            sender : username
                        }),
                        {});
                    }
                };
            }, onError);
        }

        function onError(error) {
            console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
        }

        function onMessageReceived(payload) {
            var message = JSON.parse(payload.body);
            if(message.type === 'JOIN') {
                var messageElement = document.createElement('li');
                messageElement.classList.add('event-message');
                message.content = message.sender + ' 님이 입장하셨습니다.';
            } else if (message.type === 'LEAVE') {
                var messageElement = document.createElement('li');
                messageElement.classList.add('event-message');
                message.content = message.sender + ' 님이 퇴장하셨습니다.';
            } else if (message.type === 'OFFER') {
                if (message.sender != username){
                    peerConnection.setRemoteDescription(new RTCSessionDescription({
                        sdp : message.content.sdp,
                        type : message.content.type
                    }));
                    peerConnection.createAnswer(function(answer) {
                        peerConnection.setLocalDescription({
                            sdp : answer.sdp,
                            type : answer.type
                            });
                        stompClient.send('/topic/public/' + room,
                        JSON.stringify({
                            type : "ANSWER",
                            content : answer,
                            sender : username
                        }),
                        {}
                        );
                    }, function(error) {
                        // Handle error here
                    });

                    peerConnection.onicecandidate = function(event) {
                        if (event.candidate) {
                            stompClient.send('/topic/public/' + room,
                            JSON.stringify({
                                type : "CANDIDATE",
                                content : event.candidate,
                                sender : username
                            }),
                            {});
                        }
                    };
                }

            } else if (message.type === 'CANDIDATE') {
                if (message.sender != username){
                    peerConnection.addIceCandidate({
                        candidate : message.content.candidate,
                        sdpMid : message.content.sdpMid,
                        sdpMLineIndex : message.content.sdpMLineIndex
                    })
                }
            } else if (message.type === 'ANSWER') {
                peerConnection.setRemoteDescription(new RTCSessionDescription({
                    sdp : message.content.sdp,
                    type : message.content.type
                    }));
            }
            if (message.type === 'JOIN'|| message.type === 'LEAVE'){
                var textElement = document.createElement('p');
                var messageText = document.createTextNode(message.content);
                textElement.appendChild(messageText);

                messageElement.appendChild(textElement);

                messageArea.appendChild(messageElement);
                messageArea.scrollTop = messageArea.scrollHeight;
            }
        }

        peerConnection.ondatachannel = function (event) {
            this.dataChannel = event.channel;
            console.log(this.dataChannel)
        };

        this.dataChannel.onmessage = function (event) {
            if (event.data === '비디오 시작' || event.data === '비디오 중지'
            || event.data === '음소거' || event.data === '음소거 해제'
            || event.data === '스크립트 시작' || event.data === '스크립트 중지'){
                if (event.data === '음소거'){
                    videoOutput.srcObject.getAudioTracks()[0].enabled = false
                } else if (event.data === '음소거 해제'){
                    videoOutput.srcObject.getAudioTracks()[0].enabled = true
                } else if (event.data === '비디오 시작'){
                    videoOutput.removeAttribute('style')
                } else if (event.data === '스크립트 시작'){
                    script.removeAttribute("style")
                    scriptButton.innerText = "Script OFF"
                    videoInput.classList.remove("col-8")
                    videoInput.classList.add("col-6")
                    videoOutput.classList.remove("col-8")
                    videoOutput.classList.add("col-6")
                } else if (event.data === '스크립트 중지'){
                    script.setAttribute("style", "display : none")
                    scriptButton.innerText = "Script ON"
                    videoInput.classList.remove("col-6")
                    videoInput.classList.add("col-8")
                    videoOutput.classList.remove("col-6")
                    videoOutput.classList.add("col-8")
                } else {
                    videoOutput.setAttribute("style","display:none")
                }
            }else {
                var data = JSON.parse(event.data)
                var messageElement = document.createElement('li');
                messageElement.classList.add('chat-message');

                var usernameElement = document.createElement('span');
                var usernameText = document.createTextNode(data.username);
                usernameElement.appendChild(usernameText);
                messageElement.appendChild(usernameElement);

                if (data.message != null){
                    var textElement = document.createElement('p');
                    var messageText = document.createTextNode(data.message);
                    textElement.appendChild(messageText);

                    messageElement.appendChild(textElement);

                    messageArea.appendChild(messageElement);
                    messageArea.scrollTop = messageArea.scrollHeight;
                }
            }
        };

        peerConnection.addEventListener('connectionstatechange', event => {
            if (peerConnection.connectionState === 'connected') {
                console.log("Peers connected!")
                console.log(peerConnection)
            }
        });

        var constraints = {
            video : {
                frameRate : {
                    ideal : 10,
                    max : 15
                },
                width : 1280,
                height : 720,
                facingMode : "user"
            },

            audio : true,
        };

        async function openCall() {
            let inputStream = new MediaStream();
            videoInput.srcObject = inputStream;
            const gumStream = await navigator.mediaDevices.getUserMedia(
                                constraints);
            for (const track of gumStream.getTracks()) {
                peerConnection.addTrack(track);
                inputStream.addTrack(track);
            }
        }

        peerConnection.ontrack = (event) => {
            if (event.streams && event.streams[0]) {
                videoOutput.srcObject = event.streams[0];
            } else {
                if (!inboundStream) {
                    inboundStream = new MediaStream();
                    videoOutput.srcObject = inboundStream;
                }
                inboundStream.addTrack(event.track);
            }
        };

        openCall()

    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    methods: {
        scriptControl(){
            if (scriptButton.innerText === 'Script ON'){
                this.dataChannel.send("스크립트 시작")
                script.removeAttribute("style")
                this.$refs.videoInput.classList.remove("col-8")
                this.$refs.videoInput.classList.add("col-6")
                this.$refs.videoOutput.classList.remove("col-8")
                this.$refs.videoOutput.classList.add("col-6")
                this.$refs.scriptButton.innerText = "Script OFF"
            }else {
                this.$refs.script.setAttribute("style","display : none")
                this.dataChannel.send("스크립트 중지")
                this.$refs.videoInput.classList.remove("col-6")
                this.$refs.videoInput.classList.add("col-8")
                this.$refs.videoOutput.classList.remove("col-6")
                this.$refs.videoOutput.classList.add("col-8")
                this.$refs.scriptButton.innerText = "Script ON"
            }
        },
        screenControl(){
            if (this.$refs.screen.innerText === '비디오 중지'){
                this.$refs.videoInput.setAttribute("style","display:none")
                this.dataChannel.send("비디오 중지")
                this.$refs.screen.innerText = "비디오 시작"
            }else {
                this.$refs.videoInput.setAttribute("style","display:inline")
                this.dataChannel.send("비디오 시작")
                this.$refs.screen.innerText = "비디오 중지"
            }
        },
        muteControl(){
            if (this.$refs.sound.innerText === '음소거'){
                this.dataChannel.send("음소거")
                this.$refs.sound.innerText = "음소거 해제"
            }else {
                this.dataChannel.send("음소거 해제")
                this.$refs.sound.innerText = "음소거"
            }
        },

        sendMessage() {
            var messageContent = this.$refs.messageInput.value.trim();
            if(messageContent && this.stompClient) {
                console.log(this.dataChannel)
                this.dataChannel.send(JSON.stringify({
                    "username" : "wow",
                    "message" : messageContent
                }))

                var messageElement = document.createElement('li');
                messageElement.classList.add('chat-message');

                var usernameElement = document.createElement('span');
                var usernameText = document.createTextNode("wow");
                usernameElement.appendChild(usernameText);
                messageElement.appendChild(usernameElement);

                var textElement = document.createElement('p');
                var messageText = document.createTextNode(messageContent);
                textElement.appendChild(messageText);

                messageElement.appendChild(textElement);

                this.$refs.messageArea.appendChild(messageElement);
                this.$refs.messageArea.scrollTop = this.$refs.messageArea.scrollHeight;
                this.$refs.messageInput.value = '';
            }
        }
    },
    components: { ConsultCloseModal }
};

</script>
