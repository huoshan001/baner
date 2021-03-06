package org.inn.baner.serviceimp;


import com.ztkx.transplat.container.service.ServiceException;
import com.ztkx.transplat.container.service.intface.BusinessService;
import com.ztkx.transplat.container.util.ContextUtil;
import com.ztkx.transplat.platformutil.baseUtil.BeanUtil;
import com.ztkx.transplat.platformutil.context.CommonContext;
import com.ztkx.transplat.platformutil.time.TimeUtil;
import org.apache.log4j.Logger;
import org.inn.baner.bean.Post;
import org.inn.baner.constant.Ban;
import org.inn.baner.constant.enums.BErrorCode;
import org.inn.baner.handler.data.PostData;
import org.inn.baner.handler.data.TopicData;
import org.inn.baner.handler.data.UserData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 根据主题id获取主题下帖子列表 *
 * @author zhangxiaoyun
 * 2016-3-15 16:24:11
 * <p>Company:ztkx</p>
 */
public class Ban004_ObtainPostByTopic implements BusinessService {

	private Logger logger = Logger.getLogger(Ban004_ObtainPostByTopic.class);

	@Override
	public CommonContext service(CommonContext context) throws ServiceException {

		String topicId = context.getFieldStr(Ban.topicid,CommonContext.SCOPE_GLOBAL);
		String dateFormate = "yyyyMMdd HH:mm:ss";
		logger.info("topicid ["+topicId+"]");

		List<Post> postList = null;
		PostData postData = null;
		UserData userData = null;
		TopicData topicData = null;
		try {

			postData = new PostData();
			userData = new UserData();
			topicData = new TopicData();
			String[] topicidArr = topicId.split("@", -1);
			List<Map<String, Object>> mapArrayList = new ArrayList<Map<String, Object>>();
			for (String topicidItem : topicidArr) {

				postList = postData.qryByTopicId(topicidItem);
				for (Post post : postList) {
					Map<String,Object> map = BeanUtil.objToMap(post);
					map.put(Ban.postid, post.getPostid());
					map.put(Ban.topicname, topicData.qryById(post.getTopicid()).getTopicdesc());
					map.put(Ban.mobileno, post.getCreatormobileno());
					map.put(Ban.nickname, userData.qryByMobile(post.getCreatormobileno()).getNickname());
					map.put(Ban.postname, post.getPostname());
					map.put(Ban.postdesc, post.getPostdesc());
					map.put(Ban.context, new String(post.getContext()));
					map.put(Ban.zantimes, post.getZantimes());
					map.put(Ban.topicid, post.getTopicid());
					Date createTime = post.getCreatetime();
					map.put(Ban.createtime,TimeUtil.dateFormate(dateFormate, createTime));
					map.put(Ban.updatetime, TimeUtil.dateFormate(dateFormate, post.getUpdatetime()));
					mapArrayList.add(map);
				}
			}

			context.setObj(Ban.lists, mapArrayList, CommonContext.SCOPE_GLOBAL);
			context.setFieldStr(Ban.size, String.valueOf(mapArrayList.size()), CommonContext.SCOPE_GLOBAL);
		} catch (Exception e) {
			ContextUtil.setErrorCode(BErrorCode.FAIL.code, context);
			logger.error("buss service exec exception ",e);
		}
		return context;
	}
}
