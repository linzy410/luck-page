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
      <div><a href="/manage/activity/input" class="btn btn-info btn-sm">新增</a></div>
      <div>
        <table class="table table-bordered">
          <thead>
          <tr>
            <th>名称</th>
            <th>创建日期</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <#list activitis as e>
            <tr>
            <td>${e.title}</td>
            <td>${e.createTime}</td>
            <td>
              <a class="btn btn-xs btn-info" href="/manage/activity/${e.id}">详情</a>
              <a class="btn btn-xs btn-info" target="_blank" href="/luck/${e.id}">活动</a>
            </td>
            </tr>
          </#list>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>

</html>