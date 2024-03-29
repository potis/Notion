package edu.mayo.qia.pacs.components;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Pool {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int poolKey;

  public String name;
  public String description;
  public String applicationEntityTitle;
  @Column(columnDefinition = "INTEGER")
  public boolean anonymize;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pool")
  // @JoinColumn(name = "PoolKey")
  public Set<Device> devices = new HashSet<Device>();

  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pool")
  // @JoinColumn(name = "PoolKey")
  public Script script;

  @JsonIgnore
  public Script getScript() {
    return script;
  }

  public Pool(String name, String path, String applicationEntityTitle, boolean anonymize) {
    this.name = name;
    this.description = path;
    this.applicationEntityTitle = applicationEntityTitle;
    this.anonymize = anonymize;
  }

  public Pool() {
  }

  public String toString() {
    return this.name + "(" + poolKey + ") " + "description: " + description + " AETitle: " + applicationEntityTitle;
  }

  @JsonIgnore
  public Set<Device> getDevices() {
    return devices;
  }

  // Set my values from somewhere else, but not the key!
  public void update(Pool update) {
    this.name = update.name;
    this.description = update.description;
    this.anonymize = update.anonymize;
  }
}
