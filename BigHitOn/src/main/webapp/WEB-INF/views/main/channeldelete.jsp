<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

html, body {
  margin: 0;
  padding: 0;
  background: #DCE775;
  font-family: 'Merriweather', serif;

}

h1 {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  padding: 1em;
  font-size: 2em;
  letter-spacing: .3em;
  color: #FFFFFF;
  text-align: center;
  border-top: 2px solid #E6EE9C;
  border-bottom: 2px solid #E6EE9C;
}

.contextmenu {
  display: none;
  position: absolute;
  width: 200px;
  margin: 0;
  padding: 0;
  background: #FFFFFF;
  border-radius: 5px;
  list-style: none;
  box-shadow:
    0 15px 35px rgba(50,50,90,0.1),
    0 5px 15px rgba(0,0,0,0.07);
  overflow: hidden;
  z-index: 999999;
}

.contextmenu li {
  border-left: 3px solid transparent;
  transition: ease .2s;
}

.contextmenu li a {
  display: block;
  padding: 10px;
  color: #B0BEC5;
  text-decoration: none;
  transition: ease .2s;
}

.contextmenu li:hover {
  background: #CE93D8;
  border-left: 3px solid #9C27B0;
}

.contextmenu li:hover a {
  color: #FFFFFF;
}
</style>

</head>
<body>



<ul class="contextmenu" id="channelDelBtn1">
  	<li><a href="#" id = "channelDelBtn">채널나가기</a></li>
</ul>

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">

</body>

<script type="text/javascript">


/* $(document).ready(function(){
  //Show contextmenu:
  $(document).contextmenu(function(e){
	
    //Get window size:
    var winWidth = $(document).width();
    var winHeight = $(document).height();
    //Get pointer position:
    var posX = e.pageX;
    var posY = e.pageY;
    //Get contextmenu size:
    var menuWidth = $(".contextmenu").width();
    var menuHeight = $(".contextmenu").height();
    //Security margin:
    var secMargin = 10;
    //Prevent page overflow:
    if(posX + menuWidth + secMargin >= winWidth
    && posY + menuHeight + secMargin >= winHeight){
      //Case 1: right-bottom overflow:
      posLeft = posX - menuWidth - secMargin + "px";
      posTop = posY - menuHeight - secMargin + "px";
    }
    else if(posX + menuWidth + secMargin >= winWidth){
      //Case 2: right overflow:
      posLeft = posX - menuWidth - secMargin + "px";
      posTop = posY + secMargin + "px";
    }
    else if(posY + menuHeight + secMargin >= winHeight){
      //Case 3: bottom overflow:
      posLeft = posX + secMargin + "px";
      posTop = posY - menuHeight - secMargin + "px";
    }
    else {
      //Case 4: default values:
      posLeft = posX + secMargin + "px";
      posTop = posY + secMargin + "px";
    };
    //Display contextmenu:
    $(".contextmenu").css({
      "left": posLeft,
      "top": posTop
    }).show();
    //Prevent browser default contextmenu.
    return false;
  });
  //Hide contextmenu:
  $(document).click(function(){
    $(".contextmenu").hide();
  });
}); */

</script>

</html>