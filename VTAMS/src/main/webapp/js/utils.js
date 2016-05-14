utils = {
    tmpcallback: null,
    handleCallBack: function() {
        if (utils.tmpcallback != null) {
            var content = 'OK';
            try {
                content = document.getElementsByName('frame')[0].contentWindow.document.body.getElementsByTagName("pre")[0].innerHTML;
            } catch (err) {
                console.debug("Error loading data from iframe " + err);
            }
            utils.tmpcallback(content);
        }
    }
};

