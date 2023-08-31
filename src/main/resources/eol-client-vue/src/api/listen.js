// @/api/listen.js

import request from '@/utils/request'


export function getListeningByCondition(listening) {

  return request({
    url: '/listening/condition',
    method: 'post',
    data: listening
  })
}

/* 

    @GetMapping("/{id}")
    @ApiOperation("根据id查询听力训练")
    public Result getListeningById(@PathVariable("id") Integer  id) {
        Listening listening = listeningService.getListeningById(id);
        if (listening != null) {
            return Result.ok(listening);
        } else {
            return Result.fail("查询失败，该听力训练不存在！");
        }
    }
*/
export function getListeningById(listeningId) {
  return request({
    url: '/listening/' + listeningId,
    method: 'get'
  })
}