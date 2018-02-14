/*******************************************************************************
 * Copyright (c) 2011  University of Western Australia. All rights reserved.
 * 
 * This file is part of The Ark.
 * 
 * The Ark is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * 
 * The Ark is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package au.org.theark.web.pages;

import javax.servlet.http.Cookie;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebResponse;

@SuppressWarnings("unchecked")
public class LogoutPage<T> extends WebPage
{

	public static final String	REDIRECT_PAGE	= "redirectPage";

	public LogoutPage(final CharSequence url)
	{
		doLogoutAndAddRedirect(url, 0);
	}

	public LogoutPage(final PageParameters parameters)
	{

		System.out.println("\n Constructor LogoutPage(final PageParameters param)");

		String page = parameters.getString(REDIRECT_PAGE);

		Class<? extends Page> pageClass;

		if (page != null)
		{
			try
			{
				pageClass = (Class<? extends Page>) Class.forName(page);
			}
			catch (ClassNotFoundException e)
			{
				throw new RuntimeException(e);
			}
		}
		else
		{

			System.out.println("Send the user to LoginPage");
			pageClass = LoginPage.class; // getApplication().getHomePage();
		}

		this.setStatelessHint(true);
		setResponsePage(pageClass);
		
		// this should remove the cookie...
		Subject subject = SecurityUtils.getSubject();
		//Place the selected study in session context for the user
		SecurityUtils.getSubject().getSession().removeAttribute(au.org.theark.core.Constants.STUDY_CONTEXT_ID);
		SecurityUtils.getSubject().getSession().removeAttribute(au.org.theark.core.Constants.PERSON_CONTEXT_ID);
		SecurityUtils.getSubject().getSession().removeAttribute(au.org.theark.core.Constants.PERSON_TYPE);
		subject.logout();
		Session.get().invalidateNow(); // invalidate the wicket session
		return;
	}

	private void doLogoutAndAddRedirect(final CharSequence url, final int waitBeforeRedirectInSeconds)
	{
		System.out.println("\n doLogoutAndAddRedirect() invoked");

		this.setStatelessHint(true);

		// this should remove the cookie...
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		final WebMarkupContainer redirect = new WebMarkupContainer("redirect");
		final String content = waitBeforeRedirectInSeconds + ";URL=" + url;
		redirect.add(new AttributeModifier("content", new Model<String>(content)));
		add(redirect);

		// invalidate the session
		Session.get().invalidateNow(); // invalidate the wicket session

		// HYMMMM
		Cookie c = new Cookie("rememberMe", "xxx");
		c.setMaxAge(0);
		((WebResponse) RequestCycle.get().getResponse()).addCookie(c);
	}

	@Override
	public boolean isVersioned()
	{
		return false;
	}

	public int getDelayTime()
	{
		return 0;
	}
}