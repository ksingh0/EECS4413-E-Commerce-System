/**
 * 
 */

const user_id = sessionStorage.getItem("userId")
var auctionSocket = new WebSocket(`ws://localhost:8080/ecommerce-auction-system/notif?userId=${user_id}`); 
/*
function login() {
	const userId = sessionStorage.getItem("userId");
	console.log("notifcations.js logion() " + userId); //debug
	
	auctionSocket = new WebSocket(`ws://localhost:8080/ecommerce-auction-system/notif?userId=${userId}`); 
*/
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


function bidPlaced(auctionId) {
    auctionSocket.send("A new bid was placed on auction with id " + auctionId);
	console.log("bidPlaced(): sending bid message...");
}

function auctionEnded() {
	console.log("auctionEnded(): auction ended function"); //debug
	auctionSocket.send("An auction you bidded on has ended: Auction");
}