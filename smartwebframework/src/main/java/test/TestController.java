package test;

import com.lemon.smartwebframework.core.annotation.Controller;
import com.lemon.smartwebframework.core.annotation.Inject;

@Controller
public class TestController {

	@Inject
	private ITest test;
}
