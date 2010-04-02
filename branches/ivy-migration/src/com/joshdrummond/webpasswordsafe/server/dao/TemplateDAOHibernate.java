/*
    Copyright 2009 Josh Drummond

    This file is part of WebPasswordSafe.

    WebPasswordSafe is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    WebPasswordSafe is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with WebPasswordSafe; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
package com.joshdrummond.webpasswordsafe.server.dao;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.joshdrummond.webpasswordsafe.common.model.Template;
import com.joshdrummond.webpasswordsafe.common.model.User;


/**
 * DAO implementation for Template
 * 
 * @author Josh Drummond
 *
 */
@Repository("templateDAO")
public class TemplateDAOHibernate extends GenericHibernateDAO<Template, Long> implements TemplateDAO
{

    /* (non-Javadoc)
     * @see com.joshdrummond.webpasswordsafe.server.dao.TemplateDAO#findTemplatesByUser(boolean)
     */
    public List<Template> findTemplatesByUser(User user, boolean includeShared)
    {
        if (includeShared)
        {
            return findByCriteria(Order.asc("name"), Restrictions.or(Restrictions.eq("user", user), Restrictions.eq("share", true)));
        }
        else
        {
            return findByCriteria(Order.asc("name"), Restrictions.eq("user", user));
        }
    }

}