document.addEventListener("touchstart", handleTouchStart, false);
document.addEventListener("touchmove", handleTouchMove, false);

var xDown = null;
var yDown = null;
var swipe = null;
var previousSwipe = null;

function getTouches(evt) {
  return evt.touches || evt.originalEvent.touches;
};

function handleTouchStart(evt) {
  const firstTouch = getTouches(evt)[0];
  xDown =  firstTouch.clientX;
  yDown =  firstTouch.clientY;
};

function handleTouchMove(evt) {
  if(! xDown || ! yDown) {
    return;
  }

  var xUP = evt.touches[0].clientX;
  var yUP = evt.touches[0].clientY;

  var xDiff = xDown - xUP;
  var yDiff = yDown - yUP;

  if(Math.abs(xDiff) > Math.abs(yDiff)) {
    if(xDiff > 0 && previousSwipe != "RIGHT") {
      swipe = "LEFT"
    }
    else if(previousSwipe != "LEFT") {
      swipe = "RIGHT";
    }
  }
  else {
    if(yDiff > 0 && previousSwipe != "DOWN") {
      swipe = "UP";
    }
    else if(previousSwipe != "UP") {
      swipe = "DOWN";
    }
  }

  //reset
  xDown = null;
  yDown = null;
  previousSwipe = swipe;
}
