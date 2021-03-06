package com.cognifide.sling.query;

import java.util.Iterator;

import org.apache.sling.api.resource.Resource;

import com.cognifide.sling.query.api.function.ResourceToIteratorFunction;
import com.cognifide.sling.query.api.function.ResourceToResourceFunction;
import com.cognifide.sling.query.iterator.ArrayIterator;
import com.cognifide.sling.query.iterator.EmptyIterator;

public class ResourceToIteratorWrapperFunction implements ResourceToIteratorFunction {

	private final ResourceToResourceFunction wrappedFunction;

	public ResourceToIteratorWrapperFunction(ResourceToResourceFunction wrappedFunction) {
		this.wrappedFunction = wrappedFunction;
	}

	@Override
	public Iterator<Resource> apply(Resource input) {
		Resource result = wrappedFunction.apply(input);
		if (result == null) {
			return EmptyIterator.INSTANCE;
		} else {
			return new ArrayIterator<Resource>(result);
		}
	}

}
