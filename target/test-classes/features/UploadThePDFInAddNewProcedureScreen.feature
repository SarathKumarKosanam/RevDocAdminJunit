Feature: UploadThePDFInAddNewProcedureScreenTest


Scenario: Uploading Valid Test PDF

Given LogIn to the Admin
When I should be able to navigate to the procedure configuration screen and upload PDF
Then I should be able to upload the PDF successfully