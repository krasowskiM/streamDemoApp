var testApp = angular.module('testApp', []);
var remoteVideo;
var peerConnectionConfig = {iceServers: [{urls: 'stun:stun.l.google.com:19302?transport=udp'}]};
var peerConnection = new webkitRTCPeerConnection(peerConnectionConfig);
var socket = new WebSocket('wss://localhost:8443/webRTCHandler');
        socket.onopen = function(){
            socket.send(JSON.stringify({helloMessage: "viewer"}));
        };
socket.onmessage = gotMessageFromServer;
peerConnection.onaddstream = gotRemoteStream;

testApp.controller('mainController', function($scope, $http){
    $http.get('/getResponse')
    .then(function(response){
        $scope.defaultResponse = response.data;
    });

});

function gotRemoteStream(event) {
    console.log('got remote stream');
    remoteVideo.srcObject = event.stream;
}

function gotDescription(description) {
    console.log('got description');
    peerConnection.setLocalDescription(description, function () {
        socket.send(JSON.stringify({'sdp': description}));
    }, function() {console.log('set description error')});
}

function createAnswerError(error){
    console.log(error);
}

function createOfferError(error){
    console.log(error);
}

function gotMessageFromServer(message) {
    console.log(message);

    var signal = JSON.parse(message.data);
    if(signal.sdp) {
        peerConnection.setRemoteDescription(new RTCSessionDescription(signal.sdp), function() {
            if(signal.sdp.type == 'offer') {
                peerConnection.createAnswer(gotDescription, createAnswerError);
            }
        });
    } else if(signal.ice) {
        peerConnection.addIceCandidate(new RTCIceCandidate(signal.ice));
    }
}