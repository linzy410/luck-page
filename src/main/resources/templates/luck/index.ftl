<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Luck</title>
  <link rel="stylesheet" href="/bootstrap-3.3.5/css/bootstrap.css">
  <link rel="stylesheet" href="/css/style.css">
  <script type="text/javascript" src="/js/jquery-3.1.0.min.js"></script>
  <script type="text/javascript" src="/js/yh.js"></script>
  <script type="text/javascript" src="/js/countUp.js"></script>
  <script>
    var lotteryNumbers;
    var interval;
    var index = 0;
    function randomNum(minNum, maxNum) {
      switch (arguments.length) {
        case 1:
          return parseInt(Math.random() * minNum + 1, 10);
          break;
        case 2:
          return parseInt(Math.random() * ( maxNum - minNum + 1 ) + minNum, 10);
          //或者 Math.floor(Math.random()*( maxNum - minNum + 1 ) + minNum );
          break;
        default:
          return 0;
          break;
      }
    }
    $(function(){
      var fworks = new Fireworks();
      var options = {
        useEasing : true,
        useGrouping : true,
        separator : '',
        decimal : '',
        prefix : '',
        suffix : ''
      };
      // 切换奖品等级
      $('.jianju-nav li').click(function(){
        $('.jianju-nav li').removeClass('active');
        $(this).addClass('active');
        $('#hide_prizeId').val($(this).data('prize-id'));
      })
      // 开始抽奖
      $('#btn-start').click(function(){
        var prizeId = $('#hide_prizeId').val();
        // if (prizeId == 0){
        //   alert('抽奖已结束');
        //   return;
        // }
        $.get('/luck/lettery/number/${activity.id}', function (data) {
          lotteryNumbers = data.data.split(',');

          // 号码滚动
          interval = setInterval(function() {
            var ori_number = lotteryNumbers[index++] + '';
            // 中奖号码
            var number = ori_number;
            if (ori_number.indexOf('win')>=0) {
              number = ori_number.substring(3);
              clearInterval(interval);
              $('#btn-start').show();
            }
            $('#show').text(number);
            if (ori_number.indexOf('win')>=0) {
              setTimeout(function(){$('#canvas-container').show();fworks.showRandom();}, 600);
              setTimeout(function(){$('#canvas-container').hide()}, 2800);
            }
            if (index == lotteryNumbers.length) {
              index = 0;
            }
          }, 50);
          $('#btn-start').hide();
          $('#btn_stop').show();
        });
      });
      // 抽奖
      $('#btn_stop').click(function(){
        $('#btn_stop').hide();
        var prizeId = $('#hide_prizeId').val();
        $.get('/luck/go/${activity.id}', {prizeId: prizeId}, function(data){
          if (data.code != 0) {
            alert('抽奖已结束');
          } else {
            lotteryNumbers.push('win'+data.data);
          }
        });

      });
    });
  </script>
</head>
<body class="bg">
<input type="hidden" id="hide_prizeId" value="${defaultPrizeId!'0'}" />
<div class="container-fluid">
  <div class=" title">
    <h4>${activity.title}</h4>
  </div>
  <div class="jianju-nav">
    <div class="col-md-12">
      <ul class="nav nav-pills" style="display: none;">
        <#list prizes as p>
        <li class="jianju <#if p_index == 0>active</#if>" data-prize-id="${p.id}"><a href="#">${p.name}</a></li>
        </#list>
      </ul>
    </div>
  </div>
  <div class="section active">
    <div style="padding-top: 80px;">
      <table class="table table-bordered table_rd">
        <tr>
          <td id="show">${defaultNumber}</td>
        </tr>
      </table>
    </div>
    <div class="float-bottom">
      <button type="button" class="btn btn-primary btn-lg btn-luck" id="btn-start">开始抽奖</button>
      <button type="button" data-jb="rd" class="btn btn-primary btn-lg btn-luck" id="btn_stop" style="display: none;">停</button>
    </div>
  </div>
</div>
<div id="canvas-container" style="display: none;"></div>
</body>

</html>
