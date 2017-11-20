package com.action;
import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.Service.ImagesService;
import com.bean.Images;
import com.opensymphony.xwork2.ModelDriven;

@Controller(value="imagesAction")
@Scope(value="prototype")
public class ImagesAction extends SuperAction implements ModelDriven<Images>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="images")
	private Images images;
	@Resource(name="imagesService")
	private ImagesService imagesService;
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	private String imstate;
	private File image; //上传的文件  
	private String imageFileName;//文件的名称
	public String Findll(){	
		imstate=request.getParameter("imstate");
		imstate=imagesService.imstate(imstate);

		int row=imagesService.count(imstate);
		request.setAttribute("row", row);
		
		String nowpages=request.getParameter("nowpage");
		List<Images> im_list=imagesService.Findll(nowpages,imstate);
		session.setAttribute("im_list",im_list);
		return "List_success";	
	}
	
	public String editor(){
		Images images=imagesService.editor(this.images.getImid());
		if(images!=null){
			request.setAttribute("images", images);
		}
		return "editor_success";
		
	}
	public String edit(){
		Integer ids=this.images.getImid();
		imagesService.edit(ids,image);	
		return "edit_success";
		
	}

	public String delete(){
		imagesService.delete(this.images.getImid());
		return "delete_success";
		
	}

	@Override
	public Images getModel() {
		// TODO Auto-generated method stub
		return images;
	}
	
	
	


}
