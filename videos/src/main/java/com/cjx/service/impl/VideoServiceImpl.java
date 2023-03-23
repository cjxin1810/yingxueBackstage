package com.cjx.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjx.clients.CategoryClient;
import com.cjx.clients.UserClient;
import com.cjx.entity.User;
import com.cjx.entity.Video;
import com.cjx.mapper.VideoMapper;
import com.cjx.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
        implements VideoService{
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private UserClient userClient;
    @Override
    public Map<String, Object> pageUsers(Integer page, Integer perPage,
                                         Integer id, String name,
                                         Integer categoryId, String upName) {
//        String category = categoryClient.getNameById(categoryId);
        List<User> list = userClient.searchName(upName);
        List<Integer> listid=new ArrayList<>();
        for (User user : list) {
            listid.add(user.getId());
        }
        //QueryWrapper<Video> qw = new QueryWrapper<>();
        LambdaQueryWrapper<Video> qw=new LambdaQueryWrapper<>();
      /*  qw.eq(!ObjectUtils.isEmpty("id"), "id", id)
                .like(!StringUtils.isEmpty("name"), "title", name)
                .eq(!ObjectUtils.isEmpty("categoryId"), "category_id", categoryId)
                .in(!StringUtils.isEmpty("upName"), "uid", listid);*/
        List<Integer> cids = categoryClient.getCids(categoryId);
        qw.eq(!ObjectUtils.isEmpty(id), Video::getId, id);
             qw.like(!StringUtils.isEmpty(name),Video::getTitle, name);
                qw.in(!ObjectUtils.isEmpty(categoryId), Video::getCategoryId, cids)
                .in(!StringUtils.isEmpty(upName), Video::getUid, listid);
        PageHelper.startPage(page, perPage);
        List<Video> video = baseMapper.selectList(qw);
        for (Video video1 : video) {
            if (!ObjectUtils.isEmpty(video1.getCategoryId())) {
                video1.setCategory(categoryClient.getNameById(video1.getCategoryId()));
            }
            if (!ObjectUtils.isEmpty(video1.getUid())) {
                video1.setUploader(userClient.selectUserById(video1.getUid()));
            }
        }
        PageInfo<Video> pageInfo = new PageInfo<>(video);
        Map<String,Object> map=new HashMap<>();
        map.put("total_count", pageInfo.getTotal());
        map.put("items", pageInfo.getList());
        return map;
    }

    @Override
    public int updateCategoryIdEqNull(Integer id) {
        UpdateWrapper<Video> update=new UpdateWrapper<>();
        update.eq(!ObjectUtils.isEmpty(id),"category_id", id)
                        .set("category_id",null);
        int update1 = baseMapper.update(null, update);
        return update1;
    }
}
