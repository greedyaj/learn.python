//unit
const box = 32;

var screenWidth = (parseInt(window.innerWidth * 0.95 / box)) * box;
var screenHeight = (parseInt(window.innerHeight * 0.95 / box)) * box;

const cvs = document.getElementById("game");
cvs.width = screenWidth;
cvs.height = screenHeight;

const ctx = cvs.getContext("2d");
// Create gradient
var grd = ctx.createLinearGradient(0, 0, cvs.width, cvs.height);
grd.addColorStop(0, "green");

// Fill with gradient
ctx.fillStyle = grd;
ctx.fillRect(0, 0, cvs.width, cvs.height);

//eat sound
const eat = new Audio();
eat.src = "../audio/eat.mp3";

const dead = new Audio();
dead.src = "../audio/dead.mp3";

//snake
var snake = [];

snake[0] = {
  x : 9 * box,
  y : 10 * box
};

//food
var food = {
  x : Math.floor(Math.random() * 17 + 1) * box,
  y : Math.floor(Math.random() * 15 + 3) * box
};

//score
var score = 0;

//direction
var d = "";

document.addEventListener("keydown", calculateDirection);

function calculateDirection(event) {
  var key = event.keyCode;
  if(key == 37 && d != "RIGHT") {
    d = "LEFT";
  }
  else if(key == 38 && d != "DOWN") {
    d = "UP";
  }
  else if(key == 39 && d != "LEFT") {
    d = "RIGHT";
  }
  else if(key == 40 && d != "UP") {
    d = "DOWN";
  }
}

//check collision
function collision(head, array) {
  for(var index = 0; index < array.length; index ++) {
    if(head.x == array[index].x && head.y == array[index].y) {
      return true;
    }
  }
  return false;
}

function draw() {
  ctx.fillStyle = grd;
  ctx.fillRect(0, 0, cvs.width, cvs.height);

  for(var index = 0; index < snake.length; index ++) {
    ctx.fillStyle = (index == 0)? "black" : "gray";
    ctx.fillRect(snake[index].x, snake[index].y, box, box);

    ctx.strokeStyle = "red";
    ctx.strokeRect(snake[index].x, snake[index].y, box, box);
  }

  ctx.fillStyle = "red";
  ctx.fillRect(food.x, food.y, box, box);

  //old head position
  var snakeX = snake[0].x;
  var snakeY = snake[0].y;

  //new head position
  if(d == "UP" || swipe == "UP") snakeY -= box;
  if(d == "DOWN" || swipe == "DOWN") snakeY += box;
  if(d == "LEFT" || swipe == "LEFT") snakeX -= box;
  if(d == "RIGHT" || swipe == "RIGHT") snakeX += box;

  if(snakeX == food.x && snakeY == food.y) {
      score ++;
      eat.play();
      food = {
        x : Math.floor(Math.random() * 17 + 1) * box,
        y : Math.floor(Math.random() * 15 + 3) * box
      };
  }
  else {
    //remove tail
    snake.pop();
  }

  //new head
  var newHead = {
    x : snakeX,
    y : snakeY
  };

  //game over
  if(snakeX < 0 || snakeX >= cvs.width || snakeY < 0 || snakeY >= cvs.height || collision(newHead, snake)) {
    clearInterval(gamePlay);
    dead.play();

    alert("Game Over");
    restart();
  }

  snake.unshift(newHead);

  ctx.fillStyle = "black";
  ctx.font = "45px Change One";
  ctx.fillText(score, box, 1 * box);
}

var gamePlay = setInterval(draw, 150);

function restart() {
  window.location.reload();
}
