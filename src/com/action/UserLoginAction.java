package com.action;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.Service.UserLoginService;
import com.bean.Hs;
import com.opensymphony.xwork2.ModelDriven;
@Controller(value="userLoginAction")
@Scope(value="prototype")
public class UserLoginAction extends SuperAction implements ModelDriven<Hs> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="hs")
	private Hs hs;
	@Resource(name="userLoginService")
	private UserLoginService userLoginService;
	public String login() {
		if (userLoginService.login()) {
			session.setAttribute("name", hs.getName());
			return "login_success";
		}
		return "login_error";
		
	}
	public String loginout(){
		if(session.getAttribute("name")!=null){
			session.removeAttribute("name");
		}
		return "loginout";
		
	}
	@Override
	public Hs getModel() {
		// TODO Auto-generated method stub
		return this.hs;
	}
}
