package com.readytalk.gradle.repos

import com.readytalk.gradle.util.PluginApplicator

class LocalRepo extends PluginApplicator {

  static String localName = 'readytalk_local'

  String userHome = System.getProperty("user.home")

  void apply(Project project) {
    super.apply(project)

    addLocal()
  }

  void addLocal() {
    project.repositories {
      ivy {
        name = localName
        url "/home/${userHome}/.ivy2/local"
        layout "maven"
      }
    }

    project.uploadArchives {
      repositories {
        byName(localName)
      }
    }
  }

}