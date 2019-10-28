<!DOCTYPE html>
<html lang="zh">
<#include "include/head.ftl">
<style>
  html,body,#wrapper,.iframe_wrap,iframe{height: 100%;}
</style>
<script>
  $(function(){
    $('iframe').css('height', $(window).height() - 60);
    $('.menu').click(function(){
      $('iframe').attr('src', $(this).data('href'));
      $('.menu').removeClass('active');
      $(this).addClass('active');
    });
  })
</script>
<body>
<div class="container" style="padding-top: 40px;">
  <div class="row">
    <div class="col-md-2">
      <div class="list-group">
        <a href="#" class="list-group-item active">菜单</a>
        <a data-href="/manage/activity/" class="menu list-group-item">活动管理</a>
      </div>
    </div>
    <div class="col-md-10">
      <div align="center" class="iframe_wrap">
        <iframe style="width: 100%; height: 100%; min-height: 636px;" id="main" frameborder="0" src="/dashborad" />
      </div>
    </div>
  </div>
</div>
</body>

</html>