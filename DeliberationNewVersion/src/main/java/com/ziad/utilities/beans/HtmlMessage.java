package com.ziad.utilities.beans;

public class HtmlMessage {

	protected String html = "";

	protected String to = "";

	protected String body = "";

	protected String topic = "";

	protected String name = "";
	
	public HtmlMessage() {

	}

	public HtmlMessage(String to, String body, String topic, String name) {
		super();
		this.to = to;
		this.body = body;
		this.topic = topic;
		this.name = name;

		this.html = "\r\n"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n"
				+ "<title>Welcome to One Touch</title>\r\n"
				+ "<style type=\"text/css\">\r\n"
				+ "#emailWrapperTable table {\r\n"
				+ "	font: 13px Arial, Verdana, Helvetica, sans-serif;\r\n"
				+ "	color: #292929;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#emailWrapperTable h1, #emailWrapperTable h2 {\r\n"
				+ "	font-family: Arial, Verdana, Helvetica, sans-serif;\r\n"
				+ "	margin-bottom: 2px;\r\n"
				+ "	font-size: 15px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#emailWrapperTable h3 {\r\n"
				+ "	font-size: 13px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "#emailWrapperTable h4 {\r\n"
				+ "	font-size: 11px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "a {\r\n"
				+ "	color: #084482;\r\n"
				+ "	text-decoration: underline;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "a.actionLink {\r\n"
				+ "	color: #000;\r\n"
				+ "	text-decoration: none;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "hr {\r\n"
				+ "	display: none;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".small {\r\n"
				+ "	font-size: 10px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".ppid {\r\n"
				+ "	color: #757575;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "p {\r\n"
				+ "	margin: 11px 0;\r\n"
				+ "	padding: 0;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".headline {\r\n"
				+ "	font-family: Helvetica Neue Light, Helvetica;\r\n"
				+ "	font-weight: 300;\r\n"
				+ "	font-size: 28px;\r\n"
				+ "	color: #0079C1;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "sup {\r\n"
				+ "	font-size: 7px !important;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "sup {\r\n"
				+ "	font-size: 7px !important;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "sup {\r\n"
				+ "	font-size: 7px !important;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "sup {\r\n"
				+ "	font-size: 7px !important;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "sup {\r\n"
				+ "	font-size: 7px !important;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "sup {\r\n"
				+ "	font-size: 7px !important;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "sup {\r\n"
				+ "	font-size: 7px !important;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "sup {\r\n"
				+ "	font-size: 7px !important;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "sup {\r\n"
				+ "	font-size: 7px !important;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".footerlink {\r\n"
				+ "	font: 13px Arial, Verdana, Helvetica, sans-serif !important;\r\n"
				+ "	color: #292929 !important;\r\n"
				+ "	font-weight: bold;\r\n"
				+ "	line-height: auto;\r\n"
				+ "	width: 530px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "a.footerlink:link {\r\n"
				+ "	color: #084482;\r\n"
				+ "	text-decoration: underline;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "a.footerlink:visited {\r\n"
				+ "	color: #820844;\r\n"
				+ "	text-decoration: none;\r\n"
				+ "}\r\n"
				+ "div.button{\r\n"
				+ "	padding: 19px;\r\n"
				+ "	border: 2px solid #007FA4;\r\n"
				+ "	color:#007FA4;\r\n"
				+ "	background-color: white;\r\n"
				+ "	font-size:18px;\r\n"
				+ "	text-decoration: none;\r\n"
				+ "}\r\n"
				+ "div.button a{\r\n"
				+ "	margin-left: 200px;\r\n"
				+ "}\r\n"
				+ "</style>\r\n"
				+ "\r\n"
				+ "<div style=\"display: none; color: #fff; font-size: 1pt;\"></div>\r\n"
				+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"emailWrapperTable\"\r\n"
				+ "	width=\"580\">\r\n"
				+ "	<tbody>\r\n"
				+ "		<tr valign=\"top\">\r\n"
				+ "			<td colspan=\"3\">\r\n"
				+ "				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
				+ "					<tbody>\r\n"
				+ "						<tr valign=\"top\">\r\n"
				+ "							<td width=\"130px;\"><br></td>\r\n"
				+ "						</tr>\r\n"
				+ "						<tr>\r\n"
				+ "							<td><img alt=\"\" border=\"0\" height=\"10\"\r\n"
				+ "								src=\"http://www.paypalobjects.com/en_US/i/scr/pixel.gif\"\r\n"
				+ "								width=\"1\"><img src=\"http://www.umi.ac.ma/wp-content/themes/umi/images/logo.png\"><br>\r\n"
				+ "							<br></td>\r\n"
				+ "						</tr>\r\n"
				+ "					</tbody>\r\n"
				+ "				</table>\r\n"
				+ "			</td>\r\n"
				+ "		</tr>\r\n"
				+ "		<tr>\r\n"
				+ "			<td colspan=\"3\"><img height=\"13\"\r\n"
				+ "				src=\"https://www.paypalobjects.com/en_US/i/scr/scr_emailTopCorners_580wx13h.gif\"\r\n"
				+ "				border=\"0\" style=\"vertical-align: bottom\" alt=\"\"></td>\r\n"
				+ "		</tr>\r\n"
				+ "		<tr>\r\n"
				+ "			<td width=\"12\"\r\n"
				+ "				style=\"background: url(/i/scr/scr_emailLeftBorder_13wx1h.gif) left repeat-y; border-left: 1px solid #ddd;\">\r\n"
				+ "				<img src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\"\r\n"
				+ "				border=\"0\" alt=\"\">\r\n"
				+ "			</td>\r\n"
				+ "\r\n"
				+ "			<td class=\"contentArea\"\r\n"
				+ "				style=\"width: 530px; word-wrap: break-word; padding: 12px; margin: 0\"\r\n"
				+ "				width=\"530\">\r\n"
				+ "				<table width=\"100%\">\r\n"
				+ "					<tbody>\r\n"
				+ "						<tr>\r\n"
				+ "							<td><span class=\"headline\"\r\n"
				+ "								style=\"font-family: Helvetica Neue Light, Helvetica; font-weight: 300; font-size: 28px; color: #0079C1;\">\r\n"
				+ "									<p style=\"font-size: 80%; color: #007FA4;\">{{titre}}</p>\r\n"
				+ "							</span>\r\n"
				+ "							<p>Cher {{nom}},</p>\r\n"
				+ "								<p>{{paragraphe}}</p>\r\n"
				+ "						</tr>\r\n"
				+ "					</tbody>\r\n"
				+ "				</table>\r\n"
				+ "			</td>\r\n"
				+ "			<td width=\"12\"\r\n"
				+ "				style=\"background: url(/i/scr/scr_emailRightBorder_13wx1h.gif) left repeat-y; border-right: 1px solid #ddd;\">\r\n"
				+ "				<img src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\"\r\n"
				+ "				border=\"0\" alt=\"\">\r\n"
				+ "			</td>\r\n"
				+ "\r\n"
				+ "		</tr>\r\n"
				+ "		<tr>\r\n"
				+ "			<td colspan=\"3\"><img height=\"13\"\r\n"
				+ "				src=\"https://www.paypalobjects.com/en_US/i/scr/scr_emailBottomCorners_580wx13h.gif\"\r\n"
				+ "				border=\"0\" alt=\"\"></td>\r\n"
				+ "		</tr>\r\n"
				+ "	</tbody>\r\n"
				+ "</table>";
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String generateMessage() {
		html.replace("{{titre}}", topic);
		html.replace("{{nom}}", name);
		html.replace("{{paragraphe}}", body);
		return html;
	}

}
