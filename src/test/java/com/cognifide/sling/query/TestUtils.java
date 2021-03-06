package com.cognifide.sling.query;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;

import com.cognifide.sling.query.mock.json.JsonToResource;

public final class TestUtils {
	private TestUtils() {
	}

	public static Resource getTree() {
		InputStream jsonStream = TestUtils.class.getClassLoader().getResourceAsStream("sample_tree.json");
		try {
			Resource resource = JsonToResource.parse(jsonStream);
			jsonStream.close();
			return resource;
		} catch (IOException e) {
			return null;
		}
	}

	public static List<Resource> iteratorToList(Iterator<Resource> iterator) {
		List<Resource> list = new ArrayList<Resource>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	public static void assertEmptyIterator(Iterator<Resource> iterator) {
		if (iterator.hasNext()) {
			Assert.fail(String.format("Iterator should be empty, but %s is returned", iterator.next()
					.toString()));
		}
	}

	public static void assertResourceSetEquals(Iterator<Resource> iterator, String... names) {
		Set<String> expectedSet = new LinkedHashSet<String>(Arrays.asList(names));
		Set<String> actualSet = new LinkedHashSet<String>(getResourceNames(iterator));
		Assert.assertEquals(expectedSet, actualSet);
	}

	public static void assertResourceListEquals(Iterator<Resource> iterator, String... names) {
		Assert.assertEquals(Arrays.asList(names), getResourceNames(iterator));
	}

	private static List<String> getResourceNames(Iterator<Resource> iterator) {
		List<String> resourceNames = new ArrayList<String>();
		while (iterator.hasNext()) {
			resourceNames.add(iterator.next().getName());
		}
		return resourceNames;
	}
}
