
package com.gu.itunes

import org.scalactic.Bad
import org.scalatest._

import scala.util.{ Failure, Success, Try }
import scala.xml.Utility.trim
import play.api.mvc.Results._

class ItunesRssFeedSpec extends FlatSpec with ItunesTestData with Matchers {

  it should "check that the produced XML for the tags is consistent" in {

    val currentXml = trim(iTunesRssFeed(itunesCapiResponse).get)

    val expectedXml = trim(
      <rss version="2.0" xmlns:itunes="http://www.itunes.com/dtds/podcast-1.0.dtd">
        <channel>
          <title>Science Weekly</title>
          <link>https://www.theguardian.com/science/series/science</link>
          <description>
            The Guardian's science team bring you the best analysis and interviews from the worlds of science and technology
          </description>
          <language>en-gb</language>
          <copyright>theguardian.com © 2014</copyright>
          <lastBuildDate></lastBuildDate>
          <ttl>15</ttl>
          <itunes:type>Serial</itunes:type>
          <itunes:owner>
            <itunes:email>userhelp@theguardian.com</itunes:email>
            <itunes:name>The Guardian</itunes:name>
          </itunes:owner>
          <itunes:image href="https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/4/22/1398182483649/ScienceWeekly.png"/>
          <itunes:author>The Guardian</itunes:author>
          <itunes:keywords/>
          <itunes:summary>
            The Guardian's science team bring you the best analysis and interviews from the worlds of science and technology
          </itunes:summary>
          <itunes:new-feed-url>https://www.theguardian.com/science/series/science/podcast.xml</itunes:new-feed-url>
          <image>
            <title>Science Weekly</title>
            <url>https://static.guim.co.uk/sitecrumbs/Guardian.gif</url>
            <link>https://www.theguardian.com</link>
          </image>
          <itunes:category text="Health">
            <itunes:category text="Fitness &amp; Nutrition"/>
          </itunes:category>
        </channel>
      </rss>)

    currentXml \ "channel" \ "title" should be(expectedXml \ "channel" \ "title")
    currentXml \ "channel" \ "link" should be(expectedXml \ "channel" \ "link")
    currentXml \ "channel" \ "description" should be(expectedXml \ "channel" \ "description")
    currentXml \ "channel" \ "language" should be(expectedXml \ "channel" \ "language")
    currentXml \ "channel" \ "copyright" should be(expectedXml \ "channel" \ "copyright")
    currentXml \ "channel" \ "type" should be(expectedXml \ "channel" \ "type")
    currentXml \ "channel" \ "ttl" should be(expectedXml \ "channel" \ "ttl")
    currentXml \ "channel" \ "owner" should be(expectedXml \ "channel" \ "owner")
    currentXml \ "channel" \ "image" should be(expectedXml \ "channel" \ "image")
    currentXml \ "channel" \ "author" should be(expectedXml \ "channel" \ "author")
    currentXml \ "channel" \ "explicit" should be(expectedXml \ "channel" \ "explicit")
    currentXml \ "channel" \ "summary" should be(expectedXml \ "channel" \ "summary")
    currentXml \ "channel" \ "image" should be(expectedXml \ "channel" \ "image")
    currentXml \ "channel" \ "category" should be(expectedXml \ "channel" \ "category")
    currentXml \ "channel" \ "new-feed-url" should be(expectedXml \ "channel" \ "new-feed-url")
  }

  it should "return a 404 if a podcast cannot be found" in {
    val attempt = Try(iTunesRssFeed(tagMissingPodcastFieldResponse))
    attempt.get match {
      case Bad(failed: Failed) =>
        failed.message should be("podcast not found")
        failed.status should be(NotFound)
        failed.toString should be("message: podcast not found, status: 404")
      case _ =>
        fail("""expected Bad(Failed("podcast not found", NotFound))""")
    }
  }

}
