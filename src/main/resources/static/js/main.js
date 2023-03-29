 var i = 1;
     var j = 1;
     function addNewContactPhone() {
     let newJ = j-1;
      let newI = i-1;
      const h2e = document.getElementById(`newAdderPhoneId-${newJ}`);
      let adderHtmlPhone = '<div class="col-md-12" id="contactPhoneAdder"> <div class="form-group bmd-form-group mt-0"> <label class="label">Phone</label> <input type="tel" class="form-control" id="personPhone" name="personPhone['+newI+']" value=""></div></div><p id="newAdderPhoneId-'+j+'"></p>';
       j++;
      h2e.insertAdjacentHTML("afterend", adderHtmlPhone);
    }
    function addNewContactInfo() {
        let newI = i-1;
      const h2 = document.getElementById(`newAdderId-${newI}`);
      let adderHtml = '<div id="contactInfoAdder" class="col-md-12 d-flex justify-content-center">  <div class="col-md-3">  <div class="form-group bmd-form-group mt-0"> <label class="label">Person Name </label><input type="text" class="form-control" id="personName" name="personName" value=""></div></div><div class="col-md-3"><div class="form-group bmd-form-group mt-0"><label class="label">Email</label><input type="email" class="form-control" id="personEmail" name="personEmail" value=""></div></div> <div class="col-md-3 d-flex justify-content-center flex-column"> <div class="col-md-12" id="contactPhoneAdder"> <div class="form-group bmd-form-group mt-0"> <label class="label">Phone</label> <input type="tel" class="form-control" id="personPhone" name="personPhone['+i+']" value=""></div></div> <p id="newAdderPhoneId-'+j+'"></p></div></div> <p id="newAdderId-'+i+'" ></p>';
       i++;
       j++;
      h2.insertAdjacentHTML("afterend", adderHtml);
    }
