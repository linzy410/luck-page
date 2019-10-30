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
          <input type="hidden" id="activity.id" value="${(activity.id)!''}">
          <div class="form-group">
            <label class="col-sm-2 control-label">活动名称</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" name="activity.title">
            </div>
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
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[0].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[0].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[0].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[0].number" /></td>
                  </tr>
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[1].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[1].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[1].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[1].number" /></td>
                  </tr>
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[2].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[2].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[2].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[2].number" /></td>
                  </tr>
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[3].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[3].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[3].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[3].number" /></td>
                  </tr>
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[4].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[4].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[4].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[4].number" /></td>
                  </tr>
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[5].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[5].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[5].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[5].number" /></td>
                  </tr>
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[6].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[6].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[6].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[6].number" /></td>
                  </tr>
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[7].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[7].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[7].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[7].number" /></td>
                  </tr>
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[8].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[8].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[8].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[8].number" /></td>
                  </tr>
                  <tr>
                    <td><input type="text" class="form-control" name="prizes[9].listOrder" /></td>
                    <td><input type="text" class="form-control" name="prizes[9].name" /></td>
                    <td><input type="text" class="form-control" name="prizes[9].amount" /></td>
                    <td><input type="text" class="form-control" name="defaultNumbers[9].number" /></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">奖池号码</label>
            <div class="col-sm-3">
              <input type="text" class="form-control" name="numberPrefix" placeholder="号码前缀">
            </div>
            <div class="col-sm-3">
              <input type="text" class="form-control" name="startLotteryNumber" placeholder="请输入起始号码">
            </div>
            <div class="col-sm-3">
              <input type="text" class="form-control" name="endLotteryNumber" placeholder="请输入结束号码">
            </div>
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