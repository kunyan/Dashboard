package org.yankun.dashboard.spring;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 
 * @author kun.yan
 *
 */
public class CustomMappingJackson2JsonView extends MappingJackson2JsonView {

	@Override
	protected Object filterModel(Map<String, Object> model) {
		Map<?, ?> result = (Map<?, ?>) super.filterModel(model);
		if (result.size() == 1) {
			return result.values().iterator().next();
		} else {
			return result;
		}
	}
}
