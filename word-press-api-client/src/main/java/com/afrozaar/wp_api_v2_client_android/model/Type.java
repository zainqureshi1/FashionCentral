package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.afrozaar.wp_api_v2_client_android.util.Validate;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Zain on 11/27/2017.
 */

public class Type extends BaseModel {

    public static final String JSON_FIELD_NAME = "name";
    public static final String JSON_FIELD_SLUG = "slug";

    @SerializedName("description")
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Type withDescription(String description) {
        setDescription(description);
        return this;
    }

    @SerializedName("link")
    private String link;

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public Type withLink(String link) {
        setLink(link);
        return this;
    }

    @SerializedName("name")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Type withName(String name) {
        setName(name);
        return this;
    }

    @SerializedName("slug")
    private String slug;

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }

    public Type withSlug(String slug) {
        setSlug(slug);
        return this;
    }

    /**
     * Links for this post; author, attachments, history, etc.
     */
    @JsonAdapter(LinksDeserializer.class)
    @SerializedName("_links")
    private ArrayList<Link> links;

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public void addLink(Link link) {
        if (links == null) {
            links = new ArrayList<>();
        }
        links.add(link);
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public Type withLinks(ArrayList<Link> links) {
        setLinks(links);
        return this;
    }

    public Type withLink(Link link) {
        addLink(link);
        return this;
    }

    public Type() {
    }

    public Type(Parcel in) {
        super(in);
        description = in.readString();
        link = in.readString();
        name = in.readString();
        slug = in.readString();
        in.readTypedList(links, Link.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(description);
        dest.writeString(link);
        dest.writeString(name);
        dest.writeString(slug);
        dest.writeTypedList(links);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Type> CREATOR = new Creator<Type>() {
        @Override
        public Type createFromParcel(Parcel source) {
            return new Type(source);
        }

        @Override
        public Type[] newArray(int size) {
            return new Type[size];
        }
    };

    @Override
    public String toString() {
        return "Type{" +
                "description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", links=" + links +
                '}';
    }
}
