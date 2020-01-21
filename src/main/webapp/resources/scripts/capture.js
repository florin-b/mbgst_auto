var canvas = document.getElementById('canvas');
var context = canvas.getContext('2d');
var video = document.getElementById('video');

if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
	navigator.mediaDevices.getUserMedia({
		video : {
			facingMode : "environment",

		}
	}).then(function(stream) {
		window.stream = stream;
		video.srcObject = stream;

	});

}

document.getElementById("snap").addEventListener("click", function() {
	context.drawImage(video, 0, 0, 640, 480);
});

document.getElementById("save").addEventListener(
		"click",
		function() {

			var image = document.getElementById("canvas")
					.toDataURL("image/png").replace("image/png",
							"image/octet-stream");

			saveSfarsitIncImg(image);

		});

function saveSfarsitIncImg(img) {

	alert('111');

	alert(img);

	try {

		$.ajax({
			type : 'GET',
			url : 'setSfarsitIncarcare',
			data : ({
				document : '123',
				codSofer : '456'
			}),
			dataType : 'json',
			contentType : 'application/json',

		});
	} catch (error) {
		alert(error);
	}

}
