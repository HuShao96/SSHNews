package com.Service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bean.Images;

import dao.ImagesDAO;
@Service(value="imagesService")
@Transactional
public class ImagesService {
	@Resource(name="imagesImpl")
	private ImagesDAO imagesDAO;
	@Resource(name="images")
	private Images images;
	private int max=5;
	public List<Images> Findll(String nowpages,String imstate){		
		if(nowpages==null||nowpages.equals("")){
			nowpages="1";
		}
		int nowpage=Integer.parseInt(nowpages);
		List<Images> im_list=null;
		if(imstate.equals("1"))
		{
			im_list=imagesDAO.SYSfindll(nowpage, max);
		}
		else{
			im_list=imagesDAO.Commonfindll(nowpage, max);
		}
		return im_list;	
	}
	
	public int count(String imstate){
		int count;
		if(imstate.equals("1"))
		{
			count=imagesDAO.SYScount();
		}
		else{
			count=imagesDAO.Commoncount();
		}
		int row=count/5;
		if(count%5!=0){
			row+=1;
		}
		return row;
	}
	
	public Images editor(Integer imid){
		Images images=null;
		if(imid!=null){
		    images=imagesDAO.findid(imid);
		}		
		return images;
	}
	public void edit(Integer imid,File image){
		imageupload(image);
		if(imid==null||imid==0){
			imagesDAO.save(images);
		}
		else {
			imagesDAO.update(images);
		}
		
	}
	private void imageupload(File image) {
		// TODO Auto-generated method stub
		String realpath = null;
		if(images.getImstate().equals("1")){
			 realpath = ServletActionContext.getServletContext().getRealPath("SystemPicture");
		}  
		else {
			
			 realpath = ServletActionContext.getServletContext().getRealPath("GeneralPicture");
		}
	        if(image != null){
	            File savefile = new File(new File(realpath), images.getImname());  
	            if(savefile.getParentFile().exists()){  
	                try {  
	                    savefile.getParentFile().mkdirs();  
	                    FileUtils.copyFile(image, savefile); 
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	               
	            }
	        }
	        Date date=new Date();
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=format.format(date);
            images.setImdate(time);
	}

	public void delete(Integer id){
		String realpath = null;
		if(images.getImstate().equals("1")){
			realpath = ServletActionContext.getServletContext().getRealPath("SystemPicture");
		}  
		else {
			realpath = ServletActionContext.getServletContext().getRealPath("GeneralPicture");
		}	
		Images images=imagesDAO.findid(id);
		File file=new File(realpath+"\\"+images.getImname());
		file.delete();
		imagesDAO.delete(images);	
	}

	public String imstate(String imstate) {
		if(imstate==null||imstate.equals("")){
			imstate=images.getImstate();
		}
		return imstate;
	}



}
