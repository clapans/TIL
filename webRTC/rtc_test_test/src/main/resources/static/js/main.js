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

var videoInput = document.getElementById('videoInput');
var videoOutput = document.getElementById('videoOutput');


var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

const configuration = {
    iceServers: [
        {
            url: 'turn:3.36.76.25:3478',
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
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    peerConnection.createOffer(function(offer) {
        peerConnection.setLocalDescription(new RTCSessionDescription({
                    sdp : offer.sdp,
                    type : offer.type
                }));
        stompClient.send("/topic/public",
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
            stompClient.send("/topic/public",
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
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        var messageElement = document.createElement('li');
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
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
                stompClient.send('/topic/public',
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
                    stompClient.send("/topic/public",
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

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

peerConnection.ondatachannel = function (event) {
    dataChannel = event.channel;
};

dataChannel.onmessage = function(event) {
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

    audio : true
};

navigator.mediaDevices.getUserMedia(constraints).
then(function(stream) {
    peerConnection.addStream(stream);
    videoInput.srcObject = stream;
}).catch(function(err) { /* handle the error */ });


peerConnection.onaddstream = function(event) {
    videoOutput.srcObject = event.stream;
};

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
