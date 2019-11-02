<!DOCTYPE html>
<html lang="zh">
<#include "../include/head.ftl">
<style>
  .text-show{padding-top: 7px;}
</style>
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
        <form class="form-horizontal" method="post" action="/manage/activity/update/defaultNumber">
          <input type="hidden" name="activity.id" value="${(activity.id)!''}">
          <div class="form-group">
            <label class="col-sm-2 control-label">活动名称</label>
            <div class="col-sm-9 text-show">${activity.title}</div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">奖项设置</label>
            <div class="col-sm-9">
              <table class="table table-bordered">
                <thead>
                  <th width="80">排序</th>
                  <th width="200">奖项名称</th>
                  <th width="80">数量</th>
                  <th>内定号码</th>
                </thead>
                <tbody>
                  <#list prizes as p>
                  <tr>
                    <td>${p.listOrder}</td>
                    <td>${p.name}</td>
                    <td>${p.amount}</td>
                    <td>
                      <input type="hidden" name="defaultNumbers[${p_index}].prizeId" value="${p.id}" />
                      <input type="text" class="form-control" name="defaultNumbers[${p_index}].number" value="${(p.defaultNumbers)!''}" />
                    </td>
                  </tr>
                  </#list>
                </tbody>
              </table>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">奖池号码</label>
            <div class="col-sm-9 text-show">
              ${startLotteryNumber!''} 至 ${endLotteryNumber!''}
            </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-primary">提交</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>

</html>