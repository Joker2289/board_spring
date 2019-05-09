var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

var radius = 10;
var dragging = false;

//context.arc(x, y, radius, start, end);
var putPoint = function(e){
	
	if(dragging){
		context.lineTo(e.clientX, e.clientY);
		context.beginPath();
		context.arc(e.offsetX, e.offsetY, radius, 0, Math.PI*2);
		context.fill();
		context.beginPath();
		context.moveTo(e.offsetX, e.offsetY);
	}
}

var engage = function(e){
	dragging = true;
	putPoint(e);
}

var disengage = function(e){
	dragging = false;
}

canvas.addEventListener("mousedown", engage);
canvas.addEventListener("mouseup", disengage);
canvas.addEventListener("mousemove", putPoint);

