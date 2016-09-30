package smart.sys.platform.springUtils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import smart.sys.platform.log.Log4jHelper;

public class SpringBeanUtil {
	private static Log4jHelper log = new Log4jHelper(SpringBeanUtil.class);

	public static Object getBean(String name) {
		ApplicationContext context = ContextHolder.getInstance()
				.getApplicationContext();
		try {
			return context.getBean(name);
		} catch (BeansException ex) {
			log.exceptionLog().error("\u83b7\u53d6spring bean\u5931\u8d25", ex);	//"获取spring bean失败"
		}
		return null;
	}

	public static Object getBean(String name, Class clazz) {
		ApplicationContext context = ContextHolder.getInstance()
				.getApplicationContext();
		try {
			return context.getBean(name, clazz);
		} catch (BeansException ex) {
			log.exceptionLog().error("\u83b7\u53d6spring bean\u5931\u8d25", ex);	//"获取spring bean失败"
		}
		return null;
	}
}
