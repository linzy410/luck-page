$(function() {
	$('.jianju-nav li').click(function(){
		$('.jianju-nav li').removeClass('active');
		$(this).addClass('active');
		$('.section').fadeOut(1000);
		var t = $(this).data('section');
		setTimeout(function(){$('#'+t).fadeIn(1000);}, 1000);
	});
	
	
	localStorage.setItem('ahm', ahm);
	var timeArray = new Array();
	var indexStr = ",";
	$('.btn-rd').click(function() {
		$(this).parent().parent().find('.table_rd>tbody>tr>td').each(function(i) {
			var o = $(this);
			var ti = setInterval(function() {preview(o);}, 50);
			timeArray[i] = ti;
		});
		$(this).toggle();
		$(this).next().toggle();
	});
	$('.btn-rd-stop').click(function() {
		$.each(timeArray, function(i, v) {
			clearInterval(v);
		})
		$(this).toggle();
		$(this).prev().toggle();
		var options = {
			useEasing : true,
			useGrouping : true,
			separator : '',
			decimal : '',
			prefix : '',
			suffix : ''
		};
		var hmStr = localStorage.getItem('ahm');
		var array = hmStr.split(',');
		indexStr = ",";
		var z ='';
		$(this).parent().parent().find('.table_rd>tbody>tr>td').each(
				function(i) {
					var i = getPreviewIndex();
					var demo = new CountUp($(this).attr('id'), parseInt($(this).text()), array[i], 0, 6, options);
					z += array[i]+',';
					demo.start();
				});
		hmStr = removeLuckhm(hmStr, indexStr.substring(1, indexStr.length-1));
		localStorage.setItem('ahm', hmStr);
	});
	function preview(obj) {
		var i = getPreviewIndex();
		var oldIndex = obj.data('index');
		indexStr = indexStr.replace(oldIndex + ',', '');
		obj.data('index', i);
		var array = localStorage.getItem('ahm').split(',');
		obj.html(array[i]);
	}
	function getPreviewIndex() {
		var array = localStorage.getItem('ahm').split(',');
		var i = Math.floor(Math.random() * array.length);
		if (indexStr.indexOf(',' + i + ',') >= 0) {
			return getPreviewIndex();
		} else {
			indexStr += i + ',';
			return i;
		}
	}
	
	/**
	 * 将已中奖的号码从号码库中删除
	 * source 逗号隔开的号码库
	 * luckhm 逗号隔开的中奖号码
	 */
	function removeLuckhm(source, luckhm) {
		var array = source.split(',');
		var source = ',' + source + ',';
		$.each(luckhm.split(','), function(i, v){
			source = source.replace(','+array[v]+',', ',');
		});
		return source.substring(1, source.length-1);
	}
});
