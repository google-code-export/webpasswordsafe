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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Type;

/**
 * POJO model for a password
 * 
 * @author Josh Drummond
 *
 */
@Entity
@Table(name="passwords")
public class Password
{
    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
    
    @Column(name="name", length=100, nullable=false)
    private String name;
    
    @Column(name="username", length=64, nullable=false, updatable=false)
    private String username;
    
    @Column(name="notes")
    @Type(type="text")
    private String notes;
    
    @Column(name="max_history", nullable=false)
    private int maxHistory;
    
    @Column(name="active", nullable=false)
    @Type(type="yes_no")
    private boolean active;
    
    @Column(name="date_created", updatable=false, nullable=false)
    private Date dateCreated;
    
    @ManyToOne
    @JoinColumn(name="user_created_id", updatable=false, nullable=false)
    private User userCreated;
    
    @Column(name="date_last_update", nullable=false)
    private Date dateLastUpdate;
    
    @ManyToOne
    @JoinColumn(name="user_last_update_id", nullable=false)
    private User userLastUpdate;
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="parent")
    @IndexColumn(name="password_position", nullable=false)
    private List<PasswordData> passwordData;
    
    @ManyToMany
    @JoinTable(name="password_tags",
            joinColumns={@JoinColumn(name="password_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")})
    private Set<Tag> tags;

    public Password()
    {
        maxHistory = -1;
        passwordData = new ArrayList<PasswordData>();
        tags = new TreeSet<Tag>();
    }
    
    public Set<Tag> getTags()
    {
        return this.tags;
    }

    public void setTags(Set<Tag> tags)
    {
        this.tags = tags;
    }

    public long getId()
    {
        return this.id;
    }
    public void setId(long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getUsername()
    {
        return this.username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getNotes()
    {
        return this.notes;
    }
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    public int getMaxHistory()
    {
        return this.maxHistory;
    }
    public void setMaxHistory(int maxHistory)
    {
        this.maxHistory = maxHistory;
    }
    public boolean isActive()
    {
        return this.active;
    }
    public void setActive(boolean active)
    {
        this.active = active;
    }
    public Date getDateCreated()
    {
        return this.dateCreated;
    }
    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }
    public User getUserCreated()
    {
        return this.userCreated;
    }
    public void setUserCreated(User userCreated)
    {
        this.userCreated = userCreated;
    }
    public Date getDateLastUpdate()
    {
        return this.dateLastUpdate;
    }
    public void setDateLastUpdate(Date dateLastUpdate)
    {
        this.dateLastUpdate = dateLastUpdate;
    }
    public User getUserLastUpdate()
    {
        return this.userLastUpdate;
    }
    public void setUserLastUpdate(User userLastUpdate)
    {
        this.userLastUpdate = userLastUpdate;
    }
    public List<PasswordData> getPasswordData()
    {
        return this.passwordData;
    }
    public void setPasswordData(List<PasswordData> passwordData)
    {
        this.passwordData = passwordData;
    }
    public void addPasswordData(PasswordData passwordDataItem)
    {
        this.passwordData.add(passwordDataItem);
    }
    public String getTagsAsString()
    {
        StringBuilder tagString = new StringBuilder();
        for (Tag tag : tags)
        {
            tagString.append(tag.getName()).append(" ");
        }
        return tagString.toString().trim();
    }
}
