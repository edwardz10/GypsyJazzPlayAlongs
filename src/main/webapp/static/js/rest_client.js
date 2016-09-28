var app = angular.module('demo', []);

var current_track;
var tracks;
var track_ids;

app.controller('Tracks', function($scope, $http) {
    $http.get('http://localhost:8080/GypsyJazzPlayAlongs/track/').
        then(function(response) {
            $scope.tracks = response.data;
            $scope.current_track = $scope.tracks[0];
            tracks = $scope.tracks;
            current_track = $scope.current_track;
            
            tracks.forEach(function(item) {
            	track_ids.push(item.id);
            });
        });
});

function onRowClick (row) {
    current_track = tracks[row.sectionRowIndex];
    player.loadVideoById(current_track.id);
}

function onYouTubeIframeAPIReady() {
  player = new YT.Player('player', {
	height: '300',
	playerVars: { 'autoplay': 0, 'controls': 0, 'showinfo': 0, 'rel': 0},
	width: '600',
	events: {
	  'onReady': onPlayerReady
	}
  });
}

// when ready, wait for clicks
function onPlayerReady(event) {
	var player = event.target;
	iframe = document.getElementById('player');
	player.loadVideoById(current_track.id);
	setupListener(); 
  
	updateTimerDisplay();
	updateProgressBar();
	
	time_update_interval = setInterval(function () {
		updateTimerDisplay();
		updateProgressBar();
	}, 1000);		  
}

function setupListener (){
	document.getElementById('full').addEventListener('click', playFullscreen);
}

function playFullscreen (){
  player.playVideo();//won't work on mobile
  
  var requestFullScreen = iframe.requestFullScreen || iframe.mozRequestFullScreen || iframe.webkitRequestFullScreen;
  if (requestFullScreen) {
	requestFullScreen.bind(iframe)();
  }
}

function loadPlaylist_video_ids() {
	player.loadPlaylist({
		'playlist': track_ids,
		'listType': 'playlist',
		'index': 0,
		'startSeconds': 0,
		'suggestedQuality': 'small'
	});
}


/*Громкость*/
function editVolume () {
	
	if (player.getVolume() == 0) {
		player.setVolume('100');
	} else {
		player.setVolume('0');
	}
}

/*Качество*/
function editQuality () {
	player.setPlaybackQuality('medium');			
	document.getElementById('quality').innerHTML = '480';
}

// This function is called by initialize()
function updateTimerDisplay(){
	document.getElementById('time').innerHTML = formatTime(player.getCurrentTime());
}

function formatTime(time){
	time = Math.round(time);
	var minutes = Math.floor(time / 60),
	seconds = time - minutes * 60;
	seconds = seconds < 10 ? '0' + seconds : seconds;
	return minutes + ":" + seconds;
}

// This function is called by initialize()
function updateProgressBar(){
	// Update the value of our progress bar accordingly.
	var line_width = jQuery('#line').width();
	var persent = (player.getCurrentTime() / player.getDuration());
	jQuery('.viewed').css('width', persent * line_width);
	per = persent * 100;
	jQuery('#fader').css('left', per+'%');
}

/*Линия прогресса*/
function progress (event) {
	
	var line_width = jQuery('#line').width();
	// положение элемента
	var pos = jQuery('#line').offset();
	var elem_left = pos.left;		
	// положение курсора внутри элемента
	var Xinner = event.pageX - elem_left;
	var newTime = player.getDuration() * (Xinner / line_width);
	// Skip video to new time.
	player.seekTo(newTime);
}