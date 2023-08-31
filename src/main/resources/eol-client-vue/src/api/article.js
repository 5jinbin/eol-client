// @/api/article.js

import request from '@/utils/request'

export function getArticleList(pageNum,pageSize) {
    return request({
        url: '/article/list',
        method: 'get',
        params:{
            pageNum,
            pageSize
        }
    })
}   

export function getArticleById(id) {
    return request({
        url: '/article/'+id,
        method: 'get'
    })
}

/* 

    @ApiOperation("根据条件查询文章列表")
    @PostMapping("/condition")
    public Result getArticlesByCondition(@RequestBody Article article) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        if(article.getAuthor() != null) lqw.like(Article::getAuthor,article.getAuthor());
        if(article.getTitle() != null) lqw.like(Article::getTitle,article.getTitle());

        List<Article> list = articleService.list(lqw);
        if(list != null){
            return Result.ok(list);
        } else{
            return Result.fail("查询失败，该文章不存在！");
        }
    }
*/

export function getArticleByCondition(article) {
    return request({
        url: '/article/condition',
        method: 'post',
        data: article
    })
}

