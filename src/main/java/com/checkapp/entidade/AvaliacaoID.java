package com.checkapp.entidade;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author user
 */
@Embeddable
public class AvaliacaoID implements Serializable {
    
    @Column(name = "id_inspecao")
    private Long inspecaoId;
    
    @Column(name = "id_item")
    private Long itemId;
    
    public AvaliacaoID() {}
            
    public AvaliacaoID(Long inspecaoId, Long itemId) {
        this.inspecaoId = inspecaoId;
        this.itemId = itemId;
    }
    
    public Long getInspecaoId() {
        return inspecaoId;
    }
    public Long getItemId() {
        return itemId;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.inspecaoId);
        hash = 23 * hash + Objects.hashCode(this.itemId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AvaliacaoID other = (AvaliacaoID) obj;
        if (!Objects.equals(this.inspecaoId, other.inspecaoId)) {
            return false;
        }
        return Objects.equals(this.itemId, other.itemId);
    }

    
}
