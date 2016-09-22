$(function(){
  localStorage.setItem('ahm', ahm);
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
    var array = localStorage.getItem('ahm').split(',');
    indexStr = ",";
    $('.table_st>tbody>tr>td').each(function(i){
      console.log(array);
      var i = getPreviewIndex();
      console.log(i + "  |  " + array[i])
      var demo = new CountUp($(this).attr('id'), parseInt($(this).text()), array[i], 0, 8, options);
      demo.start();
      array.splice(i,1);
    });
    localStorage.setItem('ahm', array);
  });
  function preview(obj) {
    var i = getPreviewIndex();
    var oldIndex = obj.data('index');
    indexStr=indexStr.replace(oldIndex+',', '');
    obj.data('index', i);
    var array = localStorage.getItem('ahm').split(',');
    obj.html(array[i]);
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
