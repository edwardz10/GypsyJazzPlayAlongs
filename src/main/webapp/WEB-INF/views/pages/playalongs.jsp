<!doctype html>
<html ng-app="demo">
	<head>
		<title>Gypsy Jazz Play Alongs</title>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.min.js"></script>
		<script src="https://www.youtube.com/iframe_api"></script>
    	<script src="static/js/rest_client.js"></script>

		<style>
			.ad-left {
			  float: left;
			}
			.ad-right {
			  float: right;
			  margin-left: 10px;
			}
			.right-buttons {
				float: right
			}	
			body {
				margin: 0;			
			}
			.panel {
				width: 600px; 
				height: 40px; 
				background: grey;
				margin-top: -5px;
				border: 1px solid #aaa;
			}
			#play, #back, #next, #pause {
				float: left;
				width: 50px;
				height: 40px;
			}
			#volume, #full {
				display: inline
			}
			#time{
				float: left;
				width: 50px;
				height: 40px;
				color: #fff;
				padding-top: 10px;
				margin-left: 10px;
			}
			#line{
				margin-top: 15px;	
				height: 4px;
				width: 350px;
				background: #fff;
				float: left;
				margin-right: 15px;
			}
			#quality{
				float: left;
				width: 50px;
				height: 40px;
				color: #fff;
				padding-top: 10px;
				margin-left: 10px;
			}
			#fader {
				background: black;
				border-radius: 5px;
				width: 10px;
				height: 10px;
				position: relative;
				z-index: 4;
				bottom: 3px;
			}
			#playlist {
				margin-top: 20px;
			}
			.viewed {
				position: absolute;
				background: red;
				height: 4px;
			}	 
	    </style>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>    	
	</head>

	<body>
		<div ng-controller="Tracks">
			<div class="ad-left">
				<div id="player"></div>	
				<div class="panel">
					<button id="back" onclick="player.previousVideo();">Back</button>
					<button id="play" onclick="player.playVideo();">Play</button>
					<button id="pause" onclick="player.pauseVideo();">Pause</button>
					<button id="next" onclick="player.nextVideo();">Next</button>
					<div id="time">00:00</div>		
					<div id="line" onclick="progress (event);">
						<div class="viewed"></div>
						<div id="fader"></div>
					</div>
					<button id="volume" onclick="editVolume ();">Volume</button>
<!-- 				<div id="quality">Auto</div> -->
					<button id="full" onclick="playFullscreen ();">FullScreen</button>
				</div>		
				<div class="right-buttons"">
					<button id="playlist" onclick="loadPlaylist_video_ids();">Load New Playlist</button>
					<button id="qual" onclick="editQuality ();">Edit quality to 480</button>
				</div>
			</div>
			<div class="ad-right">
				<div class="panel panel-default">
		                <!-- Default panel contents -->
		              <div class="panel-heading"><span class="lead">List of tracks </span></div>
		              <div class="tablecontainer">
		                  <table class="table table-hover">
		                      <thead>
		                          <tr>
		                              <th>Name.</th>
<!--	                              <th>Youtube ID</th> -->
		                          </tr>
		                      </thead>
		                      <tbody>
		                          <tr ng-repeat="t in tracks" onclick="onRowClick(this)">
		                              <td><span ng-bind="t.name"></span></td>
<!--	                              <td><span ng-bind="t.id"></span></td> -->
		                          </tr>
		                      </tbody>
		                  </table>
		              </div>
		          </div>
	          </div>		
		</div>
	</body>
</html>