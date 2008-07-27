/*
    Copyright 2008 Josh Drummond

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
package com.joshdrummond.webpasswordsafe.server.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * POJO model for a template
 * 
 * @author Josh Drummond
 *
 */
@Entity
@Table(name="templates")
public class Template
{
    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    
    @Column(name="name", length=100, nullable=false, unique=true)
    private String name;
    
    @Column(name="share", nullable=false)
    @Type(type="yes_no")
    private boolean share;
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="parent")
    private Set<TemplateDetail> templateDetails;

    public Template()
    {
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return this.user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isShare()
    {
        return this.share;
    }

    public void setShare(boolean share)
    {
        this.share = share;
    }

    public Set<TemplateDetail> getTemplateDetails()
    {
        return this.templateDetails;
    }

    public void setTemplateDetails(Set<TemplateDetail> templateDetails)
    {
        this.templateDetails = templateDetails;
    }
    
}
