package br.com.caelum.apigateway;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//@Component
class RateLimitingZuulFilter extends ZuulFilter {
	
	private final RateLimiter rateLimiter = RateLimiter.create(1.0 / 30.0);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		try {
			RequestContext requestContext = RequestContext.getCurrentContext();
			HttpServletResponse response = requestContext.getResponse();

			if(!rateLimiter.tryAcquire()) {
				requestContext.setSendZuulResponse(false);
				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
				PrintWriter writer;
				writer = response.getWriter();
				writer.append("Muitos requests! Muitos mesmo!");
			}

		} catch (IOException e) {
			//throw new RuntimeException(e);
			//ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

}
