/**
 * 
 */

var auctionSocket;

auctionSocket = new WebSocket("ws://localhost:8080/ecommerce-auction-system/notif"); 
console.log("test log in and create socket"); //debug


auctionSocket.onopen = function (event) {
		console.log("Connection established for bidding updates.");
};
		
auctionSocket.onmessage = function (event) {
	   document.getElementById("notification").innerHTML += "<p>" + event.data + "</p>";
	   console.log(event.data);
};

auctionSocket.onclose = function (event) {
	    console.log("Connection closed.");
};
		
auctionSocket.onerror = function (error) {
	   console.error("WebSocket Error: " + error);
};
	

function bidPlaced() {
	
    console.log("sending bid message...");
    auctionSocket.send("A new bid was placed on auction with id " + auctionId);
}

function auctionEnded() {
	console.log("auction ended function"); //debug
	auctionSocket.send("An auction you bidded on has ended: Auction " + auctionId);
}