$(function() {
	$('.jianju-nav li').click(function(){
		$('.jianju-nav li').removeClass('active');
		$(this).addClass('active');
		$('.section').removeClass('active');
		$('.section').fadeOut(1000);
		var t = $(this).data('section');
		setTimeout(function(){$('#'+t).fadeIn(1000);$('#'+t).addClass('active')}, 1000);
	});
	localStorage.setItem('ahm', ahm);
	localStorage.setItem('spc', 0);
	var spc=0,rdc=0,ndc=0,stc=0;
	
	var timeArray = new Array();
	var indexStr = ",";
	$('.btn-rd').click(function() {
		if (spc<2){
			$(this).parent().parent().find('.table_rd>tbody>tr>td').each(function(i) {
				var o = $(this);
				var ti = setInterval(function() {preview(o);}, 50);
				timeArray[i] = ti;
			});
			$(this).toggle();
			$(this).next().toggle();
			$(this).blur();
		} else{
			$('.nav li,.section.active').fadeOut(1000);
			$('.section').removeClass('active');
			$('#section_sp').addClass('active');
			setTimeout(function(){
				$('.nav li:last').fadeIn(1000);
				$('#section_sp').fadeIn(1000);
			}, 1000);
		}
	});
	$('.btn-sp').click(function(){
		$(this).parent().parent().find('.table_rd>tbody>tr>td').each(function(i) {
			var o = $(this);
			var ti = setInterval(function() {preview(o);}, 50);
			timeArray[i] = ti;
		});
		$(this).toggle();
		$(this).next().toggle();
		$(this).blur();
	});
	$('.btn-rd-stop').click(function() {
		$(this).hide();
		var jb = $(this).data('jb');
		if (jb=='st'){
			spc+=1;
		}
		if (jb=='rd'){
			rdc+=1;
		}
		if (jb=='nd'){
			ndc+=1;
		}
		if (jb!='sp'){
			setTimeout(function(){
				if (spc >=2){
					$('div.active .btn-rd').text('开启最终大奖');
				}
				$('div.active .btn-rd').fadeIn(2000);
				}, 1000 * 0.5);
		}
		$.each(timeArray, function(i, v) {
			clearInterval(v);
		})
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
		$(this).parent().parent().find('.table_rd>tbody>tr>td').each(
				function(i) {
					var i = getPreviewIndex();
					var hm = array[i];
					hm = getRealHm(jb, hm);
					var demo = new CountUp($(this).attr('id'), parseInt($(this).text()), hm, 0, 6, options);
					demo.start();
				});
		hmStr = removeLuckhm(hmStr, indexStr.substring(1, indexStr.length-1));
		localStorage.setItem('ahm', hmStr);
	});
	
	function getRealHm(jb, hm){
		if (jb=='st'){
			if (st.length>0){
				hm=st.shift();
			}
		} else if (jb=='rd'){
			if (rd.length>0){
				hm=rd.shift();
			}
		} else if (jb=='nd'){
			if (nd.length>0){
				hm=nd.shift();
			}
		} else if (jb=='sp'){
			if (sp.length>0){
				hm=sp.shift();
			}
		}
		return hm;
	}
		
	function showSp() {
		var height = $(window).height();
		$("#section_sp").animate({top:'0px', height:height}, 2000, function(){$("#section_sp").removeClass('sec-sp-bg').addClass('bg');});
		$("#section_sp").animate({width:'100%'}, 2000);
	}
	
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
	
	$(document).keydown(function(e){
        var e = e || window.event;
       if(e.keyCode==32) {
    	   if (!$('div.active .btn-rd-stop').is(':hidden')){
    		   $('div.active .btn-rd-stop').trigger('click');
    	   }
       }
    })
});
