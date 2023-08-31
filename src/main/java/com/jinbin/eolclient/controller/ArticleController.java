package com.jinbin.eolclient.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinbin.eolclient.pojo.Listening;
import com.jinbin.eolclient.util.Result;
import com.jinbin.eolclient.pojo.Article;
import com.jinbin.eolclient.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "文章模块")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/")
    @ApiOperation(value = "添加文章")
    public Result addArticle(@RequestBody Article article) {
        boolean flag = articleService.save(article);
        if (flag) {
            return Result.ok("添加成功");
        } else {
            return Result.fail("添加失败");
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除文章")
    public Result deleteArticle(@PathVariable("id") Long id) {
        boolean flag = articleService.removeById(id);
        if (flag) {
            return Result.ok("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @PutMapping("/")
    @ApiOperation(value = "更新文章")
    public Result updateArticle(@RequestBody Article article) {
        boolean flag = articleService.updateById(article);
        if (flag) {
            return Result.ok("更新成功");
        } else {
            return Result.fail("更新失败");
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID获取文章")
    public Result getArticleById(@PathVariable("id") Long id) {
        Article article = articleService.getById(id);
        if (article != null) {
            return Result.ok(article);
        } else {
            return Result.fail("文章不存在");
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取文章列表（分页）")
    public Result getArticleList(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        IPage<Article> articleIPage = articleService.page(page);
        if (articleIPage.getTotal() > 0) {
            return Result.ok(articleIPage);
        } else {
            return Result.fail("暂无文章");
        }
    }

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
}
