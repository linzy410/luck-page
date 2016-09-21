$(function(){
  localStorage.setItem('ahm', ahm);
  console.log(localStorage.getItem('ahm'));
  var timeArray = new Array();
  var indexStr = ",";
  $('.btn-1st').click(function(){
    $('.table_st>tbody>tr>td').each(function(i){
      var o = $(this);
      var ti = setInterval(function(){preview(o);}, 50);
      timeArray[i]=ti;
    });
    $(this).toggle();
    $('.btn-1st-stop').toggle();
  });
  $('.btn-1st-stop').click(function(){
    $.each(timeArray, function(i, v){
      clearInterval(v);
    })
    $(this).toggle();
    $('.btn-1st').toggle();
      var options = {
      useEasing : true,
      useGrouping : true,
      separator : '',
      decimal : '',
      prefix : '',
      suffix : ''
    };

    var demo = new CountUp("show1", parseInt($('#show1').text()), 666666, 0, 5, options);
    demo.start();

    $('.table_st>tbody>tr>td').each(function(i){
      var i = $(this).data('index');
      var array = localStorage.getItem('ahm').split(',');
      array.splice(i,1);
      localStorage.setItem('ahm', array);
    });
    console.log(localStorage.getItem('ahm'));
  });
  function preview(obj) {
    var i = getPreviewIndex();
    var oldIndex = obj.data('index');
    indexStr=indexStr.replace(oldIndex+',', '');
    obj.data('index', i);
    var array = localStorage.getItem('ahm').split(',');
    obj.html(array[i]+'|'+i);
  }
  function getPreviewIndex(){
    var array = localStorage.getItem('ahm').split(',');
    var i = Math.floor(Math.random()*array.length);
    if (indexStr.indexOf(',' + i + ',')>=0){
      return getPreviewIndex();
    }else {
      indexStr+= i+',';
      return i;
    }

  }
  function getHm(){
    var array = localStorage.getItem('ahm').split(',');
    var i = Math.floor(Math.random()*array.length);
    array.splice(i,1);
    localStorage.setItem('ahm', array);
    return array[i];
  }
});
