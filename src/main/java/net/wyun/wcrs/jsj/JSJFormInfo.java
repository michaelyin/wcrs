
package net.wyun.wcrs.jsj;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "form",
    "form_name",
    "entry"
})
public class JSJFormInfo {

    @JsonProperty("form")
    private String form;
    @JsonProperty("form_name")
    private String formName;
    @JsonProperty("entry")
    private Entry entry;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("form")
    public String getForm() {
        return form;
    }

    @JsonProperty("form")
    public void setForm(String form) {
        this.form = form;
    }

    @JsonProperty("form_name")
    public String getFormName() {
        return formName;
    }

    @JsonProperty("form_name")
    public void setFormName(String formName) {
        this.formName = formName;
    }

    @JsonProperty("entry")
    public Entry getEntry() {
        return entry;
    }

    @JsonProperty("entry")
    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
