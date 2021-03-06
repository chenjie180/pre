package com.xd.pre.modules.sys.controller;


import com.xd.pre.modules.log.annotation.SysLog;
import com.xd.pre.modules.sys.domain.SysDict;
import com.xd.pre.modules.sys.dto.DictDTO;
import com.xd.pre.modules.sys.service.ISysDictService;
import com.xd.pre.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author lihaodong
 * @since 2019-05-17
 */
@RestController
@RequestMapping("/dict")
public class SysDictController {

    @Autowired
    private ISysDictService dictService;

    /**
     * 保存字典信息
     *
     * @param sysDict
     * @return
     */
    @SysLog(descrption = "保存字典信息")
    @PostMapping
    public R save(@RequestBody SysDict sysDict) {
        return R.ok(dictService.save(sysDict));
    }

    /**
     * 获取字典列表集合
     *
     * @param page
     * @param pageSize
     * @return
     */
    @SysLog(descrption = "查询字典集合")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:dipt:view')")
    public R getList(Integer page, Integer pageSize) {
        return R.ok(dictService.selectDictList(page, pageSize));
    }


    /**
     * @Author 李号东
     * @Description 根据名称获取字典值详情
     * @Date 15:20 2019-05-26
     **/
    @GetMapping("/getDictDetailList")
    public R selectDictDetailList(@RequestParam String name) {
        return R.ok(dictService.selectDictDetailList(name));
    }

    /**
     * 更新字典
     *
     * @param dictDto
     * @return
     */
    @SysLog(descrption = "更新字典")
    @PutMapping
    public R update(@RequestBody DictDTO dictDto) {
        return R.ok(dictService.updateDict(dictDto));
    }


    /**
     * 根据id删除字典
     * @param id
     * @return
     */
    @SysLog(descrption = "根据id删除字典")
    @DeleteMapping("{id}")
    public R delete(@PathVariable("id") int id) {
        return R.ok(dictService.removeById(id));
    }

    /**
     * 根据id删除字典
     * @param name
     * @return
     */
    @SysLog(descrption = "根据name删除字典")
    @DeleteMapping("/delete")
    public R deleteName(@RequestParam String name) {
        return R.ok(dictService.deleteDictByName(name));
    }

}

