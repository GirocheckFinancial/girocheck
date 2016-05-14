
var ReportButtonsId = [];

// JScript File
function CreateMobileReportsButtons()
{
    reports = {
        "SimpleList" : "Simple List",
        "TwoSimpleLists" : "Two Simple Lists",
        "HighlightCondition" : "Highlight Condition",
        //"Shapes" : "Shapes",
        "Images" : "Images",
        "Watermark" : "Watermark",
        "Indicators" : "Indicators",
        "BarCodes" : "Bar-Codes",        
        "MultiColumnList" : "Multi-Column List",        
        "MasterDetailWithGroups" : "Master Detail With Groups",
        "ChartColumnsAndBars" : "Chart Columns And Bars",
        "ChartLinesAndSplines" : "Chart Lines And Splines",
        "ChartAreas" : "Chart Areas",
        //"ChartPieAndDoughnut" : "Chart Pie And Doughnut",
        "ChartStyles" : "Chart Styles",        
        "CrossTabWithoutColumns" : "Cross-Tab Without Columns",
        "WrappedCrossTab" : "Wrapped Cross-Tab",
        "ParametersDetailedCategories" : "Parameters Detailed Categories",
        "ParametersHighlightCondition" : "Parameters Highlight Condition",
        "ParametersSelectingCountry" : "Parameters Selecting Country",
        "ParametersDetailedOrders" : "Parameters Detailed Orders"
        };
            
    document.write("<table style=\"width: 100%\" cellpadding=\"0\" cellspacing=\"0\">");    
    
    for (var reportName in reports)
    {    
        document.write("<tr>");
        document.write("<td style=\"padding: 5px 0 5px 0; width: 100%\">");        
            
            ReportButtonsId.push(reportName);
            document.write("<table id=\"ReportIs_" + reportName + "\" style=\"width: 100%; cursor: pointer;\" cellpadding=\"0\" cellspacing=\"0\"" +                
                " onmouseover=\"OverReportButton(this);\" onmouseout=\"OutReportButton(this);\" " + 
                " onclick=\"SetSelectedReportButton(this); SendCommandToViewer('" + reportName + "'); \" >");
            
            document.write("<tr>");
            document.write("<td style=\"text-align: center; width: 100%;\">");
            document.write("<img src=\"images/" + reportName + ".png" + "\">");
            document.write("</td>");
            document.write("</tr>");
        
            document.write("<tr>");
            document.write("<td style=\"padding: 0 5px 0 5px; font-family: Arial; font-size: 11pt; color: #4a465c; display: block; text-align: center;\">");
            document.write(reports[reportName]);
            document.write("</td>");
            document.write("</tr>");
            document.write("</table>");
        
        document.write("</td>");
        document.write("</tr>");
    }
    
    document.write("</table>");
    
    SetSelectedReportButton(document.getElementById("ReportIs_SimpleList"));
}


function SetSelectedReportButton(button)
{    
    for (var i = 0; i < ReportButtonsId.length; i++) 
    {
        var reportButton = document.getElementById("ReportIs_" + ReportButtonsId[i]);
        reportButton.isSelected = false;
        reportButton.className = "ReportButton";
    } 
    button.isSelected = true;
    button.className = "ReportButtonSelected";
}

function OverReportButton(button)
{    
    if (button.isSelected) return;
    button.className = "ReportButtonOver";
}

function OutReportButton(button)
{    
    if (button.isSelected) return;
    button.className = "ReportButton";
}

function HideLeftPanel(thisButton)
{    
    mainLeftPanel = document.getElementById("MainLeftPanel");
    rightPanel = document.getElementById("RightPanel");
    showButton = document.getElementById("ShowButton");
    
    mainLeftPanel.style.display = "none";
    rightPanel.style.marginLeft = "0px";
    thisButton.style.display = "none";
    showButton.style.display = "";
}

function ShowLeftPanel(thisButton)
{    
    mainLeftPanel = document.getElementById("MainLeftPanel");
    rightPanel = document.getElementById("RightPanel");
    hideButton = document.getElementById("HideButton");
    
    mainLeftPanel.style.display = "";
    rightPanel.style.marginLeft = "115px";
    thisButton.style.display = "none";
    hideButton.style.display = "";
}

function SendCommandToViewer(reportName) 
{
	var _actionGetReportSnapshot = jsStiJavaWebViewer.options.actionGetReportSnapshot;
	jsStiJavaWebViewer.options.actionGetReportSnapshot = jsStiJavaWebViewer.options.actionGetReportSnapshot + "&demo=" +reportName; 
	isReportRecieved = false;
	jsStiJavaWebViewer.options.isParametersReceived = false;
	
    jsStiJavaWebViewer.options.parametersPanel.clearParameters();	
	jsStiJavaWebViewer.options.parameters = null;
	
	jsStiJavaWebViewer.options.haveBookmarks = false;
	jsStiJavaWebViewer.options.bookmarks.changeVisibleState(false);
	jsStiJavaWebViewer.options.pageNumber = 0;
	
	jsStiJavaWebViewer.postAction(null);
	jsStiJavaWebViewer.options.actionGetReportSnapshot = _actionGetReportSnapshot; 
}