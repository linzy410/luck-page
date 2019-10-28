<!DOCTYPE html>
<html lang="zh">
<#include "../include/head.ftl">
<body>
<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div>
        <ol class="breadcrumb">
          <li><span class="glyphicon glyphicon-home"></span>&nbsp;
            <a href="#">主页</a>
          </li>
          <li class="active">活动</li>
        </ol>
      </div>
      <div class="row">
        <form class="form-horizontal" method="post" action="/manage/activity/">
          <div class="form-group">
            <label class="col-sm-2 control-label">活动名称</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" name="activity.name">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-default">提交</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>

</html>