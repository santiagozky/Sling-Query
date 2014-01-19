package com.cognifide.sling.query;

import static com.cognifide.sling.query.api.SlingQuery.$;
import static com.cognifide.sling.query.TestUtils.assertResourceListEquals;
import static com.cognifide.sling.query.TestUtils.assertEmptyIterator;

import org.apache.sling.api.resource.Resource;
import org.junit.Test;

import com.cognifide.sling.query.api.SlingQuery;

public class NextPrevTest {

	private static final String PAR_PATH = "home/java/labels/jcr:content/par";

	private Resource tree = TestUtils.getTree();

	@Test
	public void testNext() {
		SlingQuery query = $(tree.getChild(PAR_PATH).getChild("configvalue")).next();
		assertResourceListEquals(query.iterator(), "configvalue_0");
	}

	@Test
	public void testPrev() {
		SlingQuery query = $(tree.getChild(PAR_PATH).getChild("configvalue")).prev();
		assertResourceListEquals(query.iterator(), "richtext");
	}

	@Test
	public void testNextFiltered() {
		SlingQuery query = $(tree.getChild(PAR_PATH).getChild("configvalue")).next(
				"demo/core/components/configValue");
		assertResourceListEquals(query.iterator(), "configvalue_0");
	}

	@Test
	public void testPrevFiltered() {
		SlingQuery query = $(tree.getChild(PAR_PATH).getChild("configvalue")).prev(
				"demo/core/components/richtext");
		assertResourceListEquals(query.iterator(), "richtext");
	}

	@Test
	public void testNextInvalidFiltered() {
		SlingQuery query = $(tree.getChild(PAR_PATH).getChild("configvalue")).next("cq:Undefined");
		assertEmptyIterator(query.iterator());
	}

	@Test
	public void testPrevInvalidFiltered() {
		SlingQuery query = $(tree.getChild(PAR_PATH).getChild("configvalue")).prev("cq:Undefined");
		assertEmptyIterator(query.iterator());
	}

	@Test
	public void testNextOnLast() {
		SlingQuery query = $(tree.getChild(PAR_PATH).getChild("configvalue_2")).next();
		assertEmptyIterator(query.iterator());
	}

	@Test
	public void testPrevOnFirst() {
		SlingQuery query = $(tree.getChild(PAR_PATH).getChild("richtext")).prev();
		assertEmptyIterator(query.iterator());
	}

	@Test
	public void testNextOnRoot() {
		SlingQuery query = $(tree).next();
		assertEmptyIterator(query.iterator());
	}

	@Test
	public void testPrevOnRoot() {
		SlingQuery query = $(tree).prev();
		assertEmptyIterator(query.iterator());
	}
}