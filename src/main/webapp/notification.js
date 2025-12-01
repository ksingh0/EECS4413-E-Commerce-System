/**
 * 
 */

const user_id = sessionStorage.getItem("userId")
var bidSocket;
var auctionSocket = new WebSocket(`ws://localhost:8080/ecommerce-auction-system/notif?userId=${user_id}`); 

auctionSocket.onopen = function (event) {
		console.log("Connection established for auction end updates.");
};
			
auctionSocket.onmessage = function (event) {
	   document.getElementById("notification").innerHTML += "<p>" + event.data + "</p>";
	   console.log(event.data);
};

auctionSocket.onclose = function (event) {
	    console.log("auctionSocket closed.");
};
			
auctionSocket.onerror = function (error) {
	   console.error("auctionSocket Error: " + error);
};


function bidPlaced(auctionId) {
	bidSocket = new WebSocket(`ws://localhost:8080/ecommerce-auction-system/bidNotif`); 
	//bid socket
	bidSocket.onopen = function (event) {
			console.log("Connection established for bidding updates.");
	};
			
	bidSocket.onmessage = function (event) {
		   document.getElementById("notification").innerHTML += "<p>" + event.data + "</p>";
		   console.log(event.data);
	};

	bidSocket.onclose = function (event) {
		    console.log("bidSocket closed.");
	};
			
	bidSocket.onerror = function (error) {
		   console.error("bidSocket Error: " + error);
	};
	
    bidSocket.send("A new bid was placed on auction with id " + auctionId);
	console.log("bidPlaced(): sending bid message...");
}

function auctionEnded() {
	console.log("auctionEnded(): auction ended function"); //debug
	auctionSocket.send("An auction you bidded on has ended: Auction");
}