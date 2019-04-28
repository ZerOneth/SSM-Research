package com.aiuiot.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiuiot.cloud_note.dao.NoteDao;
import com.aiuiot.cloud_note.dao.ShareDao;
import com.aiuiot.cloud_note.entity.Note;
import com.aiuiot.cloud_note.entity.Share;
import com.aiuiot.cloud_note.util.NoteResult;
import com.aiuiot.cloud_note.util.NoteUtil;
import com.aiuiot.cloud_note.util.ShareResult;

@Service("shareService")
@Transactional	//
public class ShareServiceImpl implements ShareService {
	@Resource
	private NoteDao noteDao;
	
	@Resource
	private ShareDao shareDao;

	//分享笔记
	public NoteResult<Object> shareNote(String noteId) {
		//向cn_share表中插入记录
		Share share = new Share();
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);	//主键-分享ID
		share.setCn_note_id(noteId);
		//获取笔记标题和内容
		Note note = noteDao.findByNoteId(noteId);
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		

				
		//保存分享记录
		shareDao.save(share);
		//update cn_note

		//模拟异常
		//String str = null;
		//str.length();
		
		
		//构建result结果
		NoteResult<Object> result = new NoteResult<Object>();
		//给result里的属性赋值
		result.setStatus(0);
		result.setMsg("添加分享笔记成功!");
		return result;
		
	}

	public NoteResult<List<Share>> searchNote(String keyword, int page) {
		//构建result
		NoteResult<List<Share>> result = new NoteResult<List<Share>>();

		String title = "%"+keyword+"%";	//拼接字符串，以实现模糊查询
		int begin = (page-1)*4;		//计算抓取记录的起点（每页显示3条数据）
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("begin", begin);
		//调用dao 进行模糊查询
		List<Share> list = shareDao.findLikeTitle(params);
		
		//设置返回结果
		result.setStatus(0);
		result.setMsg("搜索完成!");
		result.setData(list);
		
		return result;
	}	

}
