'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var candidate = null;
var stompClient = null;
var username = null;
var room = null;
var sound = document.getElementById('sound')
var screen = document.getElementById('screen')
var videoInput = document.getElementById('videoInput');
var videoOutput = document.getElementById('videoOutput');
var script = document.getElementById('userScript');
let role = null;
let inboundStream = null;
let scriptButton = document.querySelector('#script')

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

var dataChannel = peerConnection.createDataChannel("dataChannel", { reliable: true });

dataChannel.onerror = function(error) {
    console.log("Error:", error);
};
dataChannel.onclose = function() {
    console.log("Data channel is closed");
};

function connect(event) {
    username = document.querySelector('#name').value.trim();
    room = document.querySelector('#room').value.trim();
    role = document.querySelector('#role').value.trim();
    if (role === '강사') {
        scriptButton.removeAttribute("style")
    }
    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public/' + room, onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN', content: room})
    )

    peerConnection.createOffer(function(offer) {
        peerConnection.setLocalDescription(new RTCSessionDescription({
                    sdp : offer.sdp,
                    type : offer.type
                }));
        stompClient.send('/topic/public/' + room,
        {},
        JSON.stringify({
            type : "OFFER",
            content : offer,
            sender : username
        }));

        peerConnection.setLocalDescription(offer);

    }, function(error) {
        // Handle error here
    });

    peerConnection.onicecandidate = function(event) {
        if (event.candidate) {
            stompClient.send('/topic/public/' + room,
            {},
            JSON.stringify({
                type : "CANDIDATE",
                content : event.candidate,
                sender : username
            }));
        }
    };

    connectingElement.classList.add('hidden');

}

function onError(error) {
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        dataChannel.send(JSON.stringify({
            "username" : username,
            "message" : messageContent
        }))

        var messageElement = document.createElement('li');
        messageElement.classList.add('chat-message');

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(username);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(messageContent);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
        messageInput.value = '';
    }
    event.preventDefault();
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
                {},
                JSON.stringify({
                    type : "ANSWER",
                    content : answer,
                    sender : username
                }));
            }, function(error) {
                // Handle error here
            });

            peerConnection.onicecandidate = function(event) {
                if (event.candidate) {
                    stompClient.send('/topic/public/' + room,
                    {},
                    JSON.stringify({
                        type : "CANDIDATE",
                        content : event.candidate,
                        sender : username
                    }));
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
    dataChannel = event.channel;
};

dataChannel.onmessage = function(event) {
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

peerConnection.ontrack = function(event) {
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

function muteControl(event){
    if (sound.innerText === '음소거'){
        dataChannel.send("음소거")
        sound.innerText = "음소거 해제"
    }else {
        dataChannel.send("음소거 해제")
        sound.innerText = "음소거"
    }
    event.preventDefault();
}

function screenControl(event){
    if (screen.innerText === '비디오 중지'){
        videoInput.setAttribute("style","display:none")
        dataChannel.send("비디오 중지")
        screen.innerText = "비디오 시작"
    }else {
        videoInput.setAttribute("style","display:inline")
        dataChannel.send("비디오 시작")
        screen.innerText = "비디오 중지"
    }
    event.preventDefault();
}

function scriptControl(event){
    if (scriptButton.innerText === 'Script ON'){
        dataChannel.send("스크립트 시작")
        script.removeAttribute("style")
        videoInput.classList.remove("col-8")
        videoInput.classList.add("col-6")
        videoOutput.classList.remove("col-8")
        videoOutput.classList.add("col-6")
        scriptButton.innerText = "Script OFF"
    }else {
        script.setAttribute("style","display : none")
        dataChannel.send("스크립트 중지")
        videoInput.classList.remove("col-6")
        videoInput.classList.add("col-8")
        videoOutput.classList.remove("col-6")
        videoOutput.classList.add("col-8")
        scriptButton.innerText = "Script ON"
    }
    event.preventDefault();
    }

sound.addEventListener('click', muteControl, true)
screen.addEventListener('click', screenControl, true)
usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
scriptButton.addEventListener('click', scriptControl, true)

openCall()

export default WebRtc